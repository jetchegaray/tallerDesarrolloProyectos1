
function desactivar(seleccionado){
    if(seleccionado == "Filtros"){
        var tabla  = $('table');
        hijos = tabla.find('.fila-invitado');
        hijos.each(function(){
            $(this).css("display","");
        });
    }else if(seleccionado == "Civil"){
        filtrarPor('.civil');

    }else if(seleccionado == "Ceremonia"){
        filtrarPor('.ceremonia');

    }else if(seleccionado == "Fiesta"){
        filtrarPor('.fiesta');

    }
}

function filtrarPor(filtro){
        var tabla  = $('table');
        hijos = tabla.find('.fila-invitado');
        console.log(hijos);
        hijos.each(function(){
            $(this).css("display","");
            imgCivil = $(this).find(filtro);
            console.log(imgCivil);
            if(imgCivil.hasClass('not-invited')){
                 var padre = imgCivil.parent().parent().parent();
                 padre.css('display','none');
                 console.log(imgCivil);
                 console.log(padre);
            }
        });
}

$(".invites-filter").change(function () {
    //console.log("entra");
    var seleccionado = $(this).val();
    desactivar(seleccionado);
});

  $('.boton-agregar-invitado').bind('click', function(e) {
                e.preventDefault();
                $('#form-agregar-invitado').bPopup();

});
