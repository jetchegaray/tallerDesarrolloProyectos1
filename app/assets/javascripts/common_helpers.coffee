$ ->
  $("a[data-link-form=true]").click (e)->
    e.preventDefault()
    $form  = $(e.currentTarget).find('form')
    $form.submit()

  $("[data-toggle=buttons-radio] [data-value]").click (e) ->
    $this = $(this)
    value = $this.data('value')
    $input = $this.closest("[data-toggle=buttons-radio]").siblings("input")
    if ($input.val() isnt value)
      $input.val(value)
      $input.change() # Force trigger change
