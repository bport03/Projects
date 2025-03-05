extends Control

var energyState = global_stats.exp
# Called when the node enters the scene tree for the first time.
func _ready():
	pass # Replace with function body.
# Called every frame. 'delta' is the elapsed time since the previous frame.
func _process(_delta):
	energyState = (global_stats.exp/global_stats.level * 100)
	update_visuals()
func update_visuals():
	$EnergyBar/Gauge.value = energyState
	
	

"func increae_energy(num):
	energyState = clamp(energyState + num, 0, 3)
func decrease_energy(num):
	energyState = clamp(energyState - num, 0, 3)
func getenergy():
	return energyState
func setenergy(num):
	return energyState"
