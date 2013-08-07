var data=[
				{name: 'Mesa 1', max: 8,
					guests:[				
						{id: 15 , name: 'Hilary Picou', state: 'Confirmado'},
         			{id: 16, name: 'Idalia Malone', state: 'Confirmado'},
						{id: 17, name: 'Ileen Lenz', state: 'Confirmado'},
						{id: 18, name: 'Jeneva Hertzler', state: 'Confirmado'}
					]
				},
				{name: 'Mesa 2', max: 8,
					guests:[				
						{id: 19 , name: 'Kemal Zhong', state: 'Pendiente'}
					]
				},
				{name: 'Mesa 3', max: 8,
					guests:[				
						{id: 20 , name: 'Kyung Fairley', state: 'Confirmado'},
         			{id: 21 , name: 'Xyrtranna Lightouch', state: 'Confirmado'},
						{id: 22 , name: 'Xabier Aqissiaq', state: 'Confirmado'},
						{id: 23 , name: 'Willy Kleopatros', state: 'Confirmado'},
						{id: 24, name: 'Sheldon Peskin', state: 'Confirmado'}
					]
				}	,
				{name: 'Mesa 4', max: 8,
					guests:[				
						
					]
				}
			];

var picker = [
				{id: 1 , name: 'Belia Casiano', state: 'Confirmado'},
     			{id: 2 , name: 'Duncan Bothe', state: 'Confirmado'},
				{id: 3 , name: 'Ericka Loesch', state: 'Confirmado'},
				{id: 4 , name: 'Fermina Rexford', state: 'Confirmado'},
				{id: 5 , name: 'Gabriel Harrah', state: 'Confirmado'},
				{id: 6 , name: 'Sena Loso', state: 'Confirmado'},
				{id: 7 , name: 'Laurine Jacinto', state: 'Confirmado'},
				{id: 8 , name: 'Levi Myrick', state: 'Confirmado'},
				{id: 9 , name: 'Meryl Guerrant', state: 'Confirmado'},
				{id: 10 , name: 'Milissa Hove', state: 'Confirmado'},
				{id: 11, name: 'Neomi Karl', state: 'Confirmado'},
				{id: 12, name: 'Niki Dimond', state: 'Confirmado'},
				{id: 13, name: 'Odessa Melia', state: 'Confirmado'},
				{id: 14, name: 'Richelle Dials', state: 'Confirmado'}
				];

$(document).ready(function(){
	
	$.template('table_item','<div class="table"><h4>${name}<small>(Restantes <span>0</span>/<span>${max}</span>)</small></h4><ul class="unstyled guests-tables"></ul></div>');
	$.template('list_item','<li value="${id}">${name} <span class="label label-success">${state}</span></li>');
	
	var addGuestsTo = function(to,guests){
		$.each(guests,function(index,object){
			$.tmpl('list_item',object).appendTo($(to));		
			});
		};

	$.each(data,function(index,object){
			
			var table=$.tmpl('table_item',object).appendTo($('#tables-information'));
			addGuestsTo($(table).find('ul'), object.guests);				
			});
	
	addGuestsTo($('.guests-picker'), picker);
			
	$('.guests-tables').sortable(
	{
		connectWith: '.guests-tables',
		create: function(){
			var target=$($(this).parent()).find('small span')[0];
			var max=$($(this).parent()).find('small span')[1];
			var count=$(this).find('li').length;
			
			$(target).html(parseInt(max.innerHTML) - count);
		},
		receive: function(){
			var target=$($(this).parent()).find('small span')[0];
			var max=$($(this).parent()).find('small span')[1];
			var count=$(this).find('li').length;
			
			var guests_in_this_table = parseInt(max.innerHTML) - count;
			if(guests_in_this_table>=0){
				$(target).html(parseInt(max.innerHTML) - count);
			}else{
				$('.guests-tables').sortable( "cancel" );
			}

		}
	}).disableSelection();
});


