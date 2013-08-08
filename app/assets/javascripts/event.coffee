class EventController
  init: =>
    @$form = $("#event-information form")
    @$form.find(":input").change @onInputChange

  onInputChange: (e) =>
    $input = $(e.currentTarget)

    req = $.ajax @$form.action,
      type: "POST"
      data: $input.serialize()
      success: @updateEventInformation
      error: -> alert("Failed")

  updateEventInformation: (e) =>
    # alert("Done!")

# Exports
window.EventController = EventController
