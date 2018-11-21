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

  }
}
