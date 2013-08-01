function desactivar(seleccionado){
    if(seleccionado == "Filtros"){
        var tabla  = $('table');
        hijos = tabla.find('.fila-invitado');
        hijos.each(function(){
            $(this).css("display","");
        });
    }else{   
        var numeroBloque = 0;
        if(seleccionado == "Civil"){
            var tabla  = $('table');
            hijos = tabla.find('.fila-invitado');
            console.log(hijos);
            hijos.each(function(){
                imgCivil = $(this).find('.civil');
                console.log(imgCivil);
                if(imgCivil.hasClass('not-invited')){
                    var padre = imgCivil.parent().parent().parent();
                    padre.css('display','none');
                    console.log(imgCivil);
                    console.log(padre);
                }
            });       
        }else if(seleccionado == "Ceremonia"){
            numeroBloque = 2;
        }else if(seleccionado == "Fiesta"){
            numeroBloque = 3;
        }

    } 
   
   
    console.log(numeroBloque);
   
}

$(".invites-filter").change(function () {
console.log("entra");
    var seleccionado = $(this).val();
    desactivar(seleccionado);
});

  $('.boton-agregar-invitado').bind('click', function(e) {
                e.preventDefault();
                $('#form-agregar-usuario').bPopup();

});
