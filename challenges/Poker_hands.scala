import scala.collection._

import scala.io.Source

/**
 * CHALLENGE DESCRIPTION:

In the card game poker, a hand consists of five cards and are ranked, from lowest to highest, in the following way:

High Card: Highest value card.
One Pair: Two cards of the same value.
Two Pairs: Two different pairs.
Three of a Kind: Three cards of the same value.
Straight: All cards are consecutive values.
Flush: All cards of the same suit.
Full House: Three of a kind and a pair.
Four of a Kind: Four cards of the same value.
Straight Flush: All cards are consecutive values of same suit.
Royal Flush: Ten, Jack, Queen, King, Ace, in same suit.

The cards are valued in the order:
2, 3, 4, 5, 6, 7, 8, 9, Ten, Jack, Queen, King, Ace.
If two players have the same ranked hands then the rank made up of the highest value wins; 
for example, a pair of eights beats a pair of fives. 
But if two ranks tie, for example, both players have a pair of queens, then highest cards in each hand are compared; 
if the highest cards tie then the next highest cards are compared, and so on.

 */
object Main extends App {

  val src = Source.fromFile(args(0))
  val lines = src.getLines().filter(_.length > 0)

  for (line <- lines) {
    val cards = line.split(" ").map(x => Card(x.head, x.tail.head))
    val leftHand = Hand(cards.take(5));
    val rightHand = Hand(cards.takeRight(5));

    println(compare(leftHand, rightHand))
  }

  /**
   * Compares two hands
   */
  def compare(left: Hand, right: Hand): String = {
    left.compare(right) match {
      case 0 => "none"
      case 1 => "left"
      case -1 => "right"
    }
  }

}

/**
 * Playing card which has suit and value
 *
 * Spades, Hearts, Diamonds, Clubs
 * 2-10 Jack, Queen, King, Ace
 */
case class Card(value: Char, suit: Char) extends Ordered[Card] {

  import scala.math.Ordered.orderingToOrdered

  override def compare(that: Card): Int = {
    valueToNumber(this.value) compare valueToNumber(that.value)
  }

  def valueToNumber(value: Char): Int = {
    value match {
      case 'T' => 10
      case 'J' => 11
      case 'Q' => 12
      case 'K' => 13
      case 'A' => 14
      case x if (x.isDigit) => x.asDigit
    }
  }

}

/**
 * Hand contains 5 cards
 */
case class Hand(cards: Seq[Card]) extends Ordered[Hand] {

  /*
  High Card: Highest value card.
  One Pair: Two cards of the same value.
  Two Pairs: Two different pairs.
  Three of a Kind: Three cards of the same value.
  Straight: All cards are consecutive values.
  Flush: All cards of the same suit.
  Full House: Three of a kind and a pair.
  Four of a Kind: Four cards of the same value.
  Straight Flush: All cards are consecutive values of same suit.
  Royal Flush: Ten, Jack, Queen, King, Ace, in same suit.
  */

  import scala.math.Ordered.orderingToOrdered

  type Rank = Int

  val HIGH_CARD = 0
  val ONE_PAIR = 1
  val TWO_PAIR = 2
  val THREE = 3
  val STRAIGHT = 4
  val FLUSH = 5
  val FULL_HOUSE = 6
  val FOUR = 7
  val STRAIGHT_FLUSH = 8
  val ROYAL_FLUSH = 9

  def compareByHighCard(left: Seq[Card], right: Seq[Card]): Int = {
    def compare(a: Seq[Card], b: Seq[Card]) : Int = {
      a match {
        case x :: xs => a.head compare b.head match {
          case 0 => compare(a.tail, b.tail)
          case x => x
        }
        case Nil => 0
      }
    }
    compare(left.sorted.reverse.toList, right.sorted.reverse.toList)
  }

  def groupByValue(): Map[Int, Int] = {
    cards.groupBy(x => x.valueToNumber(x.value)).mapValues(_.size)
  }

  def isOnePair(): Boolean = {
    groupByValue().values.exists(_ == 2)
  }

  def isTwoPair(): Boolean = {
    groupByValue().values.filter(_ == 2).size == 2
  }

  def isThree(): Boolean = {
    groupByValue().values.exists(_ == 3)
  }

  def isFour(): Boolean = {
    groupByValue().values.exists(_ == 4)
  }

  def isFlush() : Boolean = {
    cards.groupBy(x => x.suit).size == 1
  }

  def isFullHouse() : Boolean = {
    isThree() && isOnePair()
  }

  def isStraight() : Boolean = {
    val grouped = groupByValue()
    val keys = grouped.keys.toList.sorted
    grouped.size == 5 && keys.zip(keys.tail).forall(x => x._1 == x._2 - 1)
  }

  def isRoyalFlush() : Boolean = {
    val values:Seq[Char] = cards.map(_.value)

    isFlush() && ("AJKQT".equals(values.sorted.mkString("")))
  }

  def isStraightFlush() : Boolean = {
    isStraight() && isFlush()
  }

  def compareByValue(that: Hand, value: Int): Int = {
    val value1: Int = groupByValue().find(x => x._2 == value).get._1
    val value2: Int = that.groupByValue().find(x => x._2 == value).get._1
    value1 compare value2 match {
      case 0 => compareByHighCard(cards, that.cards)
      case x => x
    }
  }

  def compareByOnePair(that: Hand): Int = compareByValue(that, 2)
  def compareByThree(that: Hand): Int = compareByValue(that, 3)
  def compareByFour(that: Hand): Int = compareByValue(that, 4)

  def compareByTwoPair(that: Hand): Int = {
    val pairs1: Iterable[Int] = groupByValue().filter(x => x._2 == 2).keys
    val pairs2: Iterable[Int] = that.groupByValue().filter(x => x._2 == 2).keys

    val a = pairs1.toList.sorted.reverse
    val b = pairs2.toList.sorted.reverse
    // compare high pair and then low pair
    a.head compare b.head match {
      case 0 => a.tail.head compare b.tail.head match {
        case 0 => compareByHighCard(cards, that.cards)
        case x => x
      }
      case x => x
    }
  }

  def compareByFullHouse(that: Hand): Int = {
    val three1: Int = groupByValue().find(x => x._2 == 3).get._1
    val three2: Int = that.groupByValue().find(x => x._2 == 3).get._1

    val pair1: Int = groupByValue().find(x => x._2 == 2).get._1
    val pair2: Int = that.groupByValue().find(x => x._2 == 2).get._1

    three1 compare three2 match {
      case 0 => pair1 compare pair2
      case x => x
    }
  }

  /**
   * Estimates rank of the hand
   *     
   * High Card: Highest value card.
    One Pair: Two cards of the same value.
    Two Pairs: Two different pairs.
    Three of a Kind: Three cards of the same value.
    Straight: All cards are consecutive values.
    Flush: All cards of the same suit.
    Full House: Three of a kind and a pair.
    Four of a Kind: Four cards of the same value.
    Straight Flush: All cards are consecutive values of same suit.
    Royal Flush: Ten, Jack, Queen, King, Ace, in same suit.
   */
  def rank() : Int = {
    if (isRoyalFlush()) ROYAL_FLUSH
    else if (isStraightFlush()) STRAIGHT_FLUSH
    else if (isFour()) FOUR
    else if (isFullHouse()) FULL_HOUSE
    else if (isFlush()) FLUSH
    else if (isStraight()) STRAIGHT
    else if (isThree()) THREE
    else if (isTwoPair()) TWO_PAIR
    else if (isOnePair()) ONE_PAIR
    else HIGH_CARD
  }
  
  override def compare(that: Hand): Int = {
    val left: Rank = rank()
    val right : Rank = that.rank()

    //print(left + " " + right)

    left.compare(right) match {
      case 0 => left match {
        case HIGH_CARD => compareByHighCard(cards, that.cards)
        case ONE_PAIR => compareByOnePair(that)
        case TWO_PAIR => compareByTwoPair(that)
        case THREE => compareByThree(that)
        case STRAIGHT => compareByHighCard(cards, that.cards)
        case FLUSH => compareByHighCard(cards, that.cards)
        case FULL_HOUSE => compareByFullHouse(that)
        case FOUR => compareByFour(that)
        case STRAIGHT_FLUSH => compareByHighCard(cards, that.cards)
        case ROYAL_FLUSH => 0
      }
      case x => x 
    }
  }
}