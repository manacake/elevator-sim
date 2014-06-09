import scala.swing._
import event._
import Swing._


class guiElevLocation(){
	var currentElevatorPos = 0
	val loc0 = new RadioButton()
	val floor3 = new RadioButton("3")
	val loc31 = new RadioButton()
	val loc32 = new RadioButton()
	val loc33 = new RadioButton()
	val loc34 = new RadioButton()
	val loc35 = new RadioButton()
	val loc36 = new RadioButton()
	val loc37 = new RadioButton()
	val loc38 = new RadioButton()
	val floor2 = new RadioButton("2")
	val loc21 = new RadioButton()
	val loc22 = new RadioButton()
	val loc23 = new RadioButton()
	val loc24 = new RadioButton()
	val loc25 = new RadioButton()
	val loc26 = new RadioButton()
	val loc27 = new RadioButton()
	val loc28 = new RadioButton()
	val floor1 = new RadioButton("1")
	val loc1 = new RadioButton()
	
	val radios = List(loc0,floor3,loc31,loc32,loc33,loc34,loc35,loc36,loc37,loc38,
												 floor2,loc21,loc22,loc23,loc24,loc25,loc26,loc27,
												 floor1)
	radios.last.selected = true
	for(e <- radios)
		e.enabled = false

	def up(){
		if (radios.head.selected) println("Can't go up.")
		else{
			for(i <- 0 to radios.length-2)
			{
				if(radios(i+1).selected)
					{
						radios(i+1).selected = false
						radios(i).selected = true
					}
			}
		}
	}
	def down(){
		if (radios.last.selected) println("Can't go down.")
		else{
			var i = radios.length - 1
			while(i >=  1)
			{
				if(radios(i-1).selected)
					{
						radios(i).selected = true
						radios(i-1).selected = false
					}
				i = i - 1
			}
		}
	}
	def atFloor3():Boolean = floor3.selected
	def atFloor2():Boolean = floor2.selected
	def atFloor1():Boolean = floor1.selected
	val panel = new BoxPanel(Orientation.Vertical)
	{
				val spacePanel = new FlowPanel()
				{
					maximumSize = (100,125)
    		  minimumSize = (100,125)
    		  preferredSize = (100,125)
				}
				maximumSize = (100,600)
    		minimumSize = (100,600)
    		preferredSize = (100,600)
    		contents += spacePanel
    		contents ++= radios
	}
}