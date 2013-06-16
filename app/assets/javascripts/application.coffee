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
    target = $target.data("target") || $target.attr("id")
    inner.load "#{$target.data("href")} ##{target}", ->
      spinner.stop()
      TaskHelper.bind()

  TaskHelper.bind()

class TaskHelper
  @bind = ->
    # Handle toggle search buttons
    $(".configuration .search-buttons button").on "click", (e) ->
      if $(this).hasClass("active") and $(".configuration .search-buttons .active").length is 1
        $(".search-results").addClass("hide")
      else
        $(".search-results").removeClass("hide")
        $(".configuration .no-search").click() if $(".configuration .no-search").hasClass("active")

    $(".configuration .no-search").on "click", (e) ->
      $(".search-buttons .active").click() unless $(this).hasClass("active")
