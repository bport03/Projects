extends Control

var healthState = global_stats.health

func _ready():
	pass
func _process(_delta):
	healthState = global_stats.health
	update_visuals()
func update_visuals():
	$LifeBar/Gauge.value = healthState
	$LifeBar/Number.text = str(healthState)

"func increae_health(num):
	healthState = clamp(healthState + num, 0, 5)
func decrease_health(num):
	healthState = clamp(healthState - num, 0, 5)
func gethealth():
	return healthState
func sethealth(num):
	return healthState"
