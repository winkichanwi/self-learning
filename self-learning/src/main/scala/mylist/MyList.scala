package mylist

trait MyList[+A] {
  def head: A

  def tail: MyList[A]

  def isEmpty: Boolean

  def ::[C >: A](e: C):  MyList[C]

  def :::[C >: A](list: MyList[C]): MyList[C]

  def map[B](f: A => B): MyList[B]

  def flatMap[B](f: A => MyList[B]): MyList[B]

  def reverse: MyList[A]

  def foldLeft[B](zero: B)(f: (B, A) => B): B

  // def foldRight
}

case class MyCons[A](head: A, tail: MyList[A]) extends MyList[A] {
  override def isEmpty = false

  override def map[B](f: A => B): MyList[B] = MyCons(f(head), tail.map(f))

  override def flatMap[B](f: A => MyList[B]): MyList[B] = {
    f(head) match {
      case MyCons(h, _) => MyCons(h, tail.flatMap(f))
      case MyNil => MyNil
    }
  }

  override def ::[C >: A](e: C): MyList[C] = MyCons(e, this)

  override def :::[C >: A](list: MyList[C]): MyList[C] = {
    if(!list.isEmpty) {
      list.head :: list.tail ::: this
    } else {
      this
    }
  }

  override def reverse: MyList[A] = {
    def _append(result: MyList[A], addList: MyList[A]): MyList[A] = {
      if (!addList.isEmpty) {
        _append(addList.head :: result, addList.tail)
      } else {
        result
      }
    }
    _append(MyNil, this)
  }

  override def foldLeft[B](zero: B)(f: (B, A) => B): B = {
    def _foldLeft(remain: MyList[A], acc: B): B = {
      remain match {
        case MyCons(h, t) => _foldLeft (t, f (acc, h))
        case MyNil => acc
      }
    }
    _foldLeft(this.tail, f(zero, this.head))
  }
}

case object MyNil extends MyList[Nothing] {
  def head: Nothing = throw new NoSuchElementException("Head of Nil")
  def tail: MyList[Nothing] = throw new UnsupportedOperationException("Tail of Nil")
  def isEmpty = true

  override def map[B](f: Nothing => B): MyList[B] = MyNil

  override def flatMap[B](f: Nothing => MyList[B]): MyList[B] = MyNil

  override def ::[C >: Nothing](e: C): MyList[C] = MyCons(e, this)

  override def :::[C >: Nothing](list: MyList[C]): MyList[C] = list

  override def reverse: MyList[Nothing] = this

  override def foldLeft[B](zero: B)(f: (B, Nothing) => B): B = zero
}
