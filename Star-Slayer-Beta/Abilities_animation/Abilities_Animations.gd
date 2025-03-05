extends AnimatedSprite2D

# variable hold selected animation to play
var animation_to_play = ""
	
# setter for the ability animation player
func set_animation_to_play(animation_name):
	animation_to_play = animation_name
	
# sets the position to play the ability animation
func set_position_to_play_at(location):
	self.position = Vector2(0,0)
	
# plays the set ability animation	
func play_animation():
	self.play(animation_to_play)

# on signal animation finished the animation is freed
func _on_animation_finished():
	queue_free()
