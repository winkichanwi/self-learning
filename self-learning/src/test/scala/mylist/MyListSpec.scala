package mylist

import org.scalatest.WordSpec

class MyListSpec extends WordSpec {

  "MyListSpec" should {

    "tail" in {
      // given
      // when
      // then
    }

    "head" in {
      // given
      // when
      // then
    }

    "map" in {
      // given
      val list = MyCons(1, MyCons(2, MyCons(3, MyNil)))

      // when
      val result = list.map(_ + 1)

      // then
      assert(result == MyCons(2, MyCons(3, MyCons(4, MyNil))))
    }

    "flatMap" in {
      // given
      val list = MyCons(1, MyCons(2, MyCons(3, MyNil)))

      // when
      val result = list.flatMap {
        e => MyCons(e + 2, MyNil)
      }

      // then
      assert(result == MyCons(3, MyCons(4, MyCons(5, MyNil))))
    }

    ":::" in {
      // given
      val list1 = MyCons(1, MyCons(2, MyCons(3, MyNil)))
      val list2 = MyCons(4, MyCons(5, MyCons(6, MyNil)))

      // when
      val result = list1 ::: list2

      // then
      assert(result == MyCons(1, MyCons(2, MyCons(3, MyCons(4, MyCons(5, MyCons(6, MyNil)))))))
    }
    "Nil :::" in {
      // given
      val list1 = MyNil
      val list2 = MyCons(4, MyCons(5, MyCons(6, MyNil)))

      // when
      val result = list1 ::: list2

      // then
      assert(result == MyCons(4, MyCons(5, MyCons(6, MyNil))))

    }

    "reverse" in {
      // given
      val list1 = MyCons(1, MyCons(2, MyCons(3, MyNil)))

      // when
      val result = list1.reverse

      // then
      assert(result == MyCons(3, MyCons(2, MyCons(1, MyNil))))
    }

    "MyNil_foldLeft" in {
      val list = MyNil

      val result = list.foldLeft(0)((acc, e) => acc + 1)
      assert(result == 0)
    }

    "MyCons_foldLeft" in {
      val list = MyCons(1, MyCons(2, MyCons(3, MyNil)))
      val result = list.foldLeft(0)((acc, e) => acc + e)
      assert(result == 6)
    }

    "MyCons_foldLeft minus" in {
      val list = MyCons(2, MyCons(2, MyCons(3, MyNil)))
      val result = list.foldLeft(0)((acc, e) => acc - e)
      assert(result == -7)
    }

    "foldRight" in {
      val list = MyCons("a", MyCons("b", MyCons("c", MyCons("d", MyNil))))
      val result = list.foldRight("")((e, acc) => acc + e)
      assert(result == "dcba")
    }
  }
}
