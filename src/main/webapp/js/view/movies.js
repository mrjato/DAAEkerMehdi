var moviesFormId = 'movies-form';
var moviesListId = 'movies-list';
var moviesFormQuery = '#' + moviesFormId;
var moviesListQuery = '#' + moviesListId;

function insertMoviesList(parent) {
	//Html dosyasý burada mý çaðýrýlacak.
	parent.append(
		'<table id="' + moviesListId + '">\
			<tr>\
				<th>Nombre</th>\
				<th>Apellido</th>\
				<th></th>\
				<th></th>\
			</tr>\
		</table>'
	);
}

function insertmoviesForm(parent) {
	parent.append(
		'<form id="' + moviesFormId + '">\
			<input name="id" type="hidden" value=""/>\
			<input name="title" type="text" value="" />\
			<input name="director" type="text" value=""/>\
			<input name="writer" type="text" value="" />\
			<input name="genres" type="text" value=""/>\
			<input name="country" type="text" value="" />\
			<input name="releaseDate" type="date" value=""/>\
			<input name="description" type="text" value="" />\
			<input id="btnAccept" type="submit" value="Create"/>\
			<input id="btnCancel" type="reset" value="Cancel"/>\
		</form>'
	);
}

function createMovieRow(movie) { //Bir satýr hazýrlanýrken Arama sonucunda
	return '<tr id="movie-'+ movie.id +'">\
		<td class="title">' + movie.title + '</td>\
		<td class="director">' + movie.director + '</td>\
		<td class="writer">' + movie.writer + '</td>\
		<td class="genres">' + movie.genres + '</td>\
		<td class="country">' + movie.country+ '</td>\
		<td class="language">' + movie.language + '</td>\
		<td class="releaseDate">' + movie.releaseDate + '</td>\
		<td class="cast">' + movie.cast + '</td>\
		<td class="runTime">' + movie.runTime + '</td>\
		<td class="description">' + movie.description + '</td>\
		<td>\
			<a class="edit" href="#">Edit</a>\
		</td>\
	</tr>';
}

function formToMovie() {//Bilgileri veritabanýna göndermek için
	var form = $(moviesFormQuery);
	return {
		'id': form.find('input[name="id"]').val(),
		'title': form.find('input[name="title"]').val(),
		'director': form.find('input[name="director"]').val(),
		'writer': form.find('input[name="writer"]').val(),
		'genres': form.find('input[name="genres"]').val(),
		'country': form.find('input[name="country"]').val(),
		'language': form.find('input[name="language"]').val(),
		'releaseDate': form.find('input[name="releaseDate"]').val(),
		'cast': form.find('input[name="cast"]').val(),
		'runTime': form.find('input[name="runTime"]').val(),
		'description': form.find('input[name="description"]').val()
	};
}

function movieToForm(movie) {//Verileri veritabanýndan çekmek için kullanýlýr
	var form = $(moviesFormQuery);
	form.find('input[name="id"]').val(movie.id);
	form.find('input[name="title"]').val(movie.title);
	form.find('input[name="director"]').val(movie.director);
	form.find('input[name="writer"]').val(movie.writer);
	form.find('input[name="genres"]').val(movie.genres);
	form.find('input[name="country"]').val(movie.country);
	form.find('input[name="language"]').val(movie.language);
	form.find('input[name="releaseDate"]').val(movie.releaseDate);
	form.find('input[name="cast"]').val(movie.cast);
	form.find('input[name="runTime"]').val(movie.runTime);
	form.find('input[name="description"]').val(movie.description);
}

function rowToMovie(id) {//?Eriþim Ýçin Mi
	var row = $('#movie-' + id);

	return {
		'id': id,
		'title': row.find('td.title').text(),
		'director': row.find('td.director').text(),
		'writer': row.find('td.writer').text(),
		'genres': row.find('td.genres').text(),
		'country': row.find('td.country').text(),
		'language': row.find('td.language').text(),
		'releaseDate': row.find('td.releaseDate').text(),
		'cast': row.find('td.cast').text(),
		'runTime': row.find('td.runTime').text(),
		'description': row.find('td.description').text()

	};
}

function isEditing() {
	return $(moviesFormQuery + ' input[name="id"]').val() != "";
}

function disableForm() {
	$(moviesFormQuery + ' input').prop('disabled', true);
}

function enableForm() {
	$(moviesFormQuery + ' input').prop('disabled', false);
}

function resetForm() {
	$(moviesFormQuery)[0].reset();
	$(moviesFormQuery + ' input[name="id"]').val('');
	$('#btnAccept').val('Create');
}

function showErrorMessage(jqxhr, textStatus, error) {
	alert(textStatus + ": " + error);
}

function addRowListeners(movie) {
	$('#movie-' + movie.id + ' a.edit').click(function() {
		movieToForm(rowToMovie(movie.id));
		$('input#btnSubmit').val('Modificar');
	});
	
	$('#movie-' + movie.id + ' a.delete').click(function() {
		if (confirm('EstÃ¡ a punto de eliminar a una moviea. Â¿EstÃ¡ seguro de que desea continuar?')) {
			deleteMovie(movie.id,
				function() {
					$('tr#movie-' + movie.id).remove();
				},
				showErrorMessage
			);
		}
	});
}

function appendToTable(movie) {
	$(moviesListQuery + ' > tbody:last')
		.append(createMovieRow(movie));
	addRowListeners(movie);
}

function initmovies() {
	$.getScript('js/dao/movies.js', function() {
		listmovies(function(movies) {
			$.each(movies, function(key, movie) {
				appendToTable(movie);
			});
		});
		
		$(moviesFormQuery).submit(function(event) {
			var movie = formToMovie();
			
			if (isEditing()) {
				modifyMovie(movie,
					function(movie) {
						$('#movie-' + movie.id + ' td.title').text(movie.title);
						$('#movie-' + movie.id + ' td.director').text(movie.director);
						$('#movie-' + movie.id + ' td.writer').text(movie.writer);
						$('#movie-' + movie.id + ' td.genres').text(movie.genres);
						$('#movie-' + movie.id + ' td.country').text(movie.country);
						$('#movie-' + movie.id + ' td.language').text(movie.language);
						$('#movie-' + movie.id + ' td.releaseDate').text(movie.releaseDate);
						$('#movie-' + movie.id + ' td.cast').text(movie.cast);
						$('#movie-' + movie.id + ' td.runTime').text(movie.runTime);
						$('#movie-' + movie.id + ' td.description').text(movie.description);
						resetForm();
					},
					showErrorMessage,
					enableForm
				);
			} else {
				addMovie(movie,
					function(movie) {
						appendToTable(movie);
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
