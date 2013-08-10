class EventController
  init: =>
    $.template('fake_task',"""
      <div id="${slug}" class="accordion-group">
          <div class="accordion-heading">
              <a class="accordion-toggle" data-toggle="collapse" data-parent="#task-list" href="#${slug}-container">
                  ${name}
              </a>
          </div>
          <div id="${slug}-container" data-href="${url}" class="accordion-body collapse" data-target="${mockupId}">
              <div class="accordion-inner">Loading</div>
          </div>
      </div>
    """)
    $.template('task', """
      <div id="${slug}" class="accordion-group">
          <div class="accordion-heading">
              <a class="accordion-toggle" data-toggle="collapse" data-parent="#task-list" href="#${slug}-container">
                  ${name}
              </a>
          </div>
          <div id="${slug}-container" data-href="${url}" class="accordion-body collapse" data-target="${slug}">
              <div class="accordion-inner">Loading</div>
          </div>
      </div>
    """)
    @$form = $("#event-information form")
    @$form.find(":input").change @onInputChange
    @$form.find("#city").chosen
      allow_single_deselect: true
      no_results_text: "Ups, no tenemos soporte para la ciudad:"

  onInputChange: (e) =>
    $target = $(e.currentTarget)
    $inputs = @$form.find(":input[name='#{$target.attr("name")}']")

    return if $target.attr("id") is "date" and $target.val() < "2013-08-15"

    req = $.ajax @$form.action,
      type: "POST"
      data: $inputs.serialize()
      success: @updateEventInformation
      error: -> alert("Failed")

  updateEventInformation: (json) =>
    @updateBudget(json)
    @updatePendingTasks(json['pendingTasks'])

  updateBudget: (json) =>
    $(".page-header .formatted-range").html(json['formatted_range'])
    $(".page-header .text-right").effect("highlight", 3000)

  updatePendingTasks: (pendingTasks) =>
    tasksBySlug = {}

    for task in pendingTasks
      tasksBySlug[task.slug] = task

      unless $("##{task.slug}").length
        taskItem = if task.mockupId
            $.tmpl('fake_task', task)
          else
            $.tmpl('task', task)

        taskItem.prependTo($('#tasks-list'))
        taskItem.effect("highlight", 3000)

    $("#tasks-list .accordion-group[id]").each ->
      unless tasksBySlug[this.id]
        $(this).hide(1000)

# Exports
window.EventController = EventController
