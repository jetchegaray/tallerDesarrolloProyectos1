$ ->
  $("a[data-link-form=true]").click (e)->
    e.preventDefault()
    $form  = $(e.currentTarget).find('form')
    $form.submit()
