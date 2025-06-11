extends Control
var isOpen: bool = false
signal opened
signal closed

# pre load and initialize the inventories elements
@onready var inventory: Inventory = preload("res://inventory/playerInventory.tres")
@onready var slots: Array = $NinePatchRect/GridContainer.get_children()

# when started update the inventory to be empty
func _ready():
	update()

# go through the inventory and the slots to add them to the GUI
func update():
	for i in range(min(inventory.items.size(), slots.size())):
		slots[i].update(inventory.items[i])
	
# when the invetory is made open set it visible and set flag	
func open():
	visible = true
	isOpen = true
	opened.emit()
	
# when the invetory is made close set it visible and set flag	
func close():
	visible = false
	isOpen = false
	closed.emit()
	
