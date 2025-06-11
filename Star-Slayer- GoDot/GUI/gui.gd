extends CanvasLayer

signal something()
# Called when the node enters the scene tree for the first time.
func _ready():
	update(global_stats.level)

# Called every frame. 'delta' is the elapsed time since the previous frame.
func _process(_delta):
	update(global_stats.level)
	pass
func update(num):
	$MainContainer/Level.text = ("Level: " + str(num))
