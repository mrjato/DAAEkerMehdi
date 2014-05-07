var musicsFormId = 'musics-form';
var musicsListId = 'musics-list';
var musicsFormQuery = '#' + musicsFormId;
var musicsListQuery = '#' + musicsListId;

function insertMusicsList(parent) {
	//Html dosyasý burada mý çaðýrýlacak.
	parent.append(
		'<table id="' + musicsListId + '">\
			<tr>\
				<th>Nombre</th>\
				<th>Apellido</th>\
				<th></th>\
				<th></th>\
			</tr>\
		</table>'
	);
}

function insertmusicsForm(parent) {
	parent.append(
		'<form id="' + musicsFormId + '">\
			<input name="id" type="hidden" value=""/>\
			<input name="albumName" type="text" value="" />\
			<input name="artist" type="text" value=""/>\
			<input name="genres" type="text" value="" />\
			<input name="releaseDate" type="date" value=""/>\
			<input name="totalRunTime" type="text" value="" />\
			<input name="songs" type="text" value=""/>\
			<input name="description" type="text" value="" />\
			<input id="btnAccept" type="submit" value="Create"/>\
			<input id="btnCancel" type="reset" value="Cancel"/>\
		</form>'
	);
}

function createMusicRow(music) { //Bir satýr hazýrlanýrken Arama sonucunda
	return '<tr id="music-'+ music.id +'">\
		<td class="albumName">' + music.albumName + '</td>\
		<td class="artist">' + music.artist + '</td>\
		<td class="genres">' + music.genres + '</td>\
		<td class="releaseDate">' + music.releaseDate + '</td>\
		<td class="totalRunTime">' + music.totalRunTime + '</td>\
		<td class="songs">' + music.songs + '</td>\
		<td class="description">' + music.description + '</td>\
		<td>\
			<a class="edit" href="#">Edit</a>\
		</td>\
	</tr>';
}


function formToMusic() {//Bilgileri veritabanýna göndermek için
	var form = $(musicsFormQuery);
	return {
		'id': form.find('input[name="id"]').val(),
		'albumName': form.find('input[name="albumName"]').val(),
		'artist': form.find('input[name="artist"]').val(),
		'genres': form.find('input[name="genres"]').val(),
		'releaseDate': form.find('input[name="releaseDate"]').val(),
		'totalRunTime': form.find('input[name="totalRunTime"]').val(),
		'songs': form.find('input[name="songs"]').val(),
		'description': form.find('input[name="description"]').val()
	};
}

function musicToForm(music) {//Verileri veritabanýndan çekmek için kullanýlýr
	var form = $(musicsFormQuery);
	form.find('input[name="id"]').val(music.id);
	form.find('input[name="albumName"]').val(music.albumName);
	form.find('input[name="artist"]').val(music.artist);
	form.find('input[name="genres"]').val(music.genres);
	form.find('input[name="releaseDate"]').val(music.releaseDate);
	form.find('input[name="totalRunTime"]').val(music.totalRunTime);
	form.find('input[name="songs"]').val(music.songs);
	form.find('input[name="description"]').val(music.description);
}

function rowToMusic(id) {//?Eriþim Ýçin Mi
	var row = $('#music-' + id);

	return {
		'id': id,
		'albumName': row.find('td.albumName').text(),
		'artist': row.find('td.artist').text(),
		'genres': row.find('td.genres').text(),
		'releaseDate': row.find('td.releaseDate').text(),
		'totalRunTime': row.find('td.totalRunTime').text(),
		'songs': row.find('td.songs').text(),
		'description': row.find('td.description').text()

	};
}

function isEditing() {
	return $(musicsFormQuery + ' input[name="id"]').val() != "";
}

function disableForm() {
	$(musicsFormQuery + ' input').prop('disabled', true);
}

function enableForm() {
	$(musicsFormQuery + ' input').prop('disabled', false);
}

function resetForm() {
	$(musicsFormQuery)[0].reset();
	$(musicsFormQuery + ' input[name="id"]').val('');
	$('#btnAccept').val('Create');
}

function showErrorMessage(jqxhr, textStatus, error) {
	alert(textStatus + ": " + error);
}

function addRowListeners(music) {
	$('#music-' + music.id + ' a.edit').click(function() {
		musicToForm(rowToMusic(music.id));
		$('input#btnSubmit').val('Modificar');
	});
	
	$('#music-' + music.id + ' a.delete').click(function() {
		if (confirm('EstÃ¡ a punto de eliminar a una musica. Â¿EstÃ¡ seguro de que desea continuar?')) {
			deleteMusic(music.id,
				function() {
					$('tr#music-' + music.id).remove();
				},
				showErrorMessage
			);
		}
	});
}

function appendToTable(music) {
	$(musicsListQuery + ' > tbody:last')
		.append(createMusicRow(music));
	addRowListeners(music);
}

function initmusics() {
	$.getScript('js/dao/musics.js', function() {
		listmusics(function(musics) {
			$.each(musics, function(key, music) {
				appendToTable(music);
			});
		});
		
		$(musicsFormQuery).submit(function(event) {
			var music = formToMusic();
			
			if (isEditing()) {
				modifyMusic(music,
					function(music) {
						$('#music-' + music.id + ' td.albumName').text(music.albumName);
						$('#music-' + music.id + ' td.artist').text(music.artist);
						$('#music-' + music.id + ' td.genres').text(music.genres);
						$('#music-' + music.id + ' td.releaseDate').text(music.releaseDate);
						$('#music-' + music.id + ' td.totalRunTime').text(music.totalRunTime);
						$('#music-' + music.id + ' td.songs').text(music.songs);
						$('#music-' + music.id + ' td.description').text(music.description);
						resetForm();
					},
					showErrorMessage,
					enableForm
				);
			} else {
				addMusic(music,
					function(music) {
						appendToTable(music);
						resetForm();
					},
					showErrorMessage,
					enableForm
				);
			}
			
			return false;
		});
		
		$('#btnAccept').click(resetForm);
	});
};
