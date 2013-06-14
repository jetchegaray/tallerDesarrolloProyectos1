# Yay!! We love coffeescript :)
# coffeescript.org
spinnerOpts =
  lines: 13
  length: 7
  width: 13
  radius: 0
  corners: 1
  rotate: 0
  direction: 1
  color: '#000'
  speed: 1
  trail: 60
  shadow: false
  hwaccel: false
  className: 'spinner'
  zIndex: 2e9
  top: 'auto'
  left: 'auto'

$ =>
	# Handle ajax tasks
	$("#task-list").on "show", (e)->

		$target = $(e.target)
		spinner = new Spinner(spinnerOpts)
		inner = $target.find(".accordion-inner")

		spinner.spin(inner.get(0))
		inner.load "#{$target.data("href")} ##{$target.attr("id")}", spinner.stop
