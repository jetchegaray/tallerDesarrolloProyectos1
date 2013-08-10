class EventController
  init: =>
    @$form = $("#event-information form")
    @$form.find(":input").change @onInputChange

  onInputChange: (e) =>
    $input = $(e.currentTarget)

    return if $input.attr("id") is "date" and $input.val() < "2013-08-15"

    req = $.ajax @$form.action,
      type: "POST"
      data: $input.serialize()
      success: @updateEventInformation
      error: -> alert("Failed")

  updateEventInformation: (json) =>
    $(".page-header .formatted-range").html(json['formatted_range'])
    $(".page-header .text-right").effect("highlight", 3000);

    # alert("Done!")

# Exports
window.EventController = EventController
