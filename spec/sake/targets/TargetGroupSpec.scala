package sake

import org.specs._ 

object TargetGroupSpec extends Specification {
    
    import sake.targets._
        
    "An empty TargetGroup" should {
        "have no targets" in {
            (new TargetGroup).targets must be_==(Nil)
        }
    }
    
    "A TargetGroup with one target" should {
        "have a list with the single target" in {
            val t1 = new Target('t1)
            val tg = new TargetGroup(t1)
            tg.targets.size must be_==(1)
            tg.targets.head must be_==(t1)
        }
    }
    
    "A TargetGroup with N target" should {
        "have a list with the N targets" in {
            val t1 = new Target('t1)
            val t2 = new Target('t2)
            val t3 = new Target('t3)
            val list = List(t1,t2,t3)
            val tg = new TargetGroup(list)
            tg.targets.size must be_==(3)
            tg.targets must be_==(list)
        }
    }
    
    "Addition using '::'" should {
        "return a new TargetGroup with the new Target prepended to the original list." in {
            val t1 = new Target('t1)
            val t2 = new Target('t2)
            val t3 = new Target('t3)
            val tg1 = new TargetGroup(List(t2,t3))
            val tg2 = t1 :: tg1
            tg1.targets must be_==(List(t2,t3))
            tg2.targets must be_==(List(t1,t2,t3))
            
        }
    }

    "Concatenation using ':::'" should {
        "return a new TargetGroup with the new Target list prepended to the original list." in {
            val t1 = new Target('t1)
            val t2 = new Target('t2)
            val t3 = new Target('t3)
            val t4 = new Target('t4)
            val tg1 = new TargetGroup(List(t1,t2))
            val tg2 = new TargetGroup(List(t3,t4))
            val tg3 = tg1 ::: tg2
            tg1.targets must be_==(List(t1,t2))
            tg2.targets must be_==(List(t3,t4))
            tg3.targets must be_==(List(t1,t2,t3,t4))
        }
    }

    "Apply()" should {
        "return a new TargetGroup with new Targets." in {
            val t1 = new Target('t1)
            val t2 = new Target('t2)
            val t3 = new Target('t3)
            val tg1 = new TargetGroup(List(t1,t2,t3))
            val tg2 = tg1 {println("hello!")}
            tg2.targets.foreach { t2 =>
                tg1.targets.find(t => t.name == t2.name) match {
                    case None => fail(t2.name.toString())
                    case Some(t1) => (t1 eq t2) must be_==(false)
                }
            }
        }
    }
}