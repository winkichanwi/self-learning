package mylist

trait MyList[+A] {
  def head: A
  def tail: MyList[A]
  def isEmpty: Boolean
//  def ::():  MyList[A]
//  def :::(): MyList[A]
  def map[B](f: A => B): MyList[B]
  def flatMap[B](f: A => MyList[B]): MyList[B]
}

case class MyCons[A](head: A, tail: MyList[A]) extends MyList[A] {
  override def isEmpty = false

  override def map[B](f: A => B): MyList[B] = MyCons(f(head), tail.map(f))

  override def flatMap[B](f: A => MyList[B]): MyList[B] = MyCons(f(head).head: B, tail.flatMap(f))
}

case object MyNil extends MyList[Nothing] {
  def head: Nothing = throw new NoSuchElementException("Head of Nil")
  def tail: MyList[Nothing] = throw new UnsupportedOperationException("Tail of Nil")
  def isEmpty = true

  override def map[B](f: Nothing => B): MyList[B] = MyNil

  override def flatMap[B](f: Nothing => MyList[B]): MyList[B] = MyNil
}
