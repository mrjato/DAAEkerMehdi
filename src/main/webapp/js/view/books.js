var booksFormId = 'books-form';
var booksListId = 'books-list';
var booksFormQuery = '#' + booksFormId;
var booksListQuery = '#' + booksListId;

function insertBooksList(parent) {
	//Html dosyasý burada mý çaðýrýlacak.
	parent.append(
		'<table id="' + booksListId + '">\
			<tr>\
				<th>Nombre</th>\
				<th>Apellido</th>\
				<th></th>\
				<th></th>\
			</tr>\
		</table>'
	);
}

function insertbooksForm(parent) {
	parent.append(
		'<form id="' + booksFormId + '">\
			<input name="id" type="hidden" value=""/>\
			<input name="title" type="text" value="" />\
			<input name="author" type="text" value=""/>\
			<input name="kind" type="text" value="" />\
			<input name="isbn" type="text" value=""/>\
			<input name="editionLanguage" type="text" value="" />\
			<input name="releaseDate" type="date" value=""/>\
			<input name="description" type="text" value="" />\
			<input id="btnAccept" type="submit" value="Create"/>\
			<input id="btnCancel" type="reset" value="Cancel"/>\
		</form>'
	);
}

function createBookRow(book) { //Bir satýr hazýrlanýrken Arama sonucunda
	return '<tr id="book-'+ book.id +'">\
		<td class="title">' + book.title + '</td>\
		<td class="author">' + book.author + '</td>\
		<td class="kind">' + book.kind + '</td>\
		<td class="isbn">' + book.isbn + '</td>\
		<td class="editionLanguage">' + book.editionLanguage + '</td>\
		<td class="releaseDate">' + book.releaseDate + '</td>\
		<td class="description">' + book.description + '</td>\
		<td>\
			<a class="edit" href="#">Edit</a>\
		</td>\
	</tr>';
}


function formToBook() {//Bilgileri veritabanýna göndermek için
	var form = $(booksFormQuery);
	return {
		'id': form.find('input[name="id"]').val(),
		'title': form.find('input[name="title"]').val(),
		'author': form.find('input[name="author"]').val(),
		'kind': form.find('input[name="kind"]').val(),
		'isbn': form.find('input[name="isbn"]').val(),
		'editionLanguage': form.find('input[name="editionLanguage"]').val(),
		'releaseDate': form.find('input[name="releaseDate"]').val(),
		'description': form.find('input[name="description"]').val()
	};
}

function bookToForm(book) {//Verileri veritabanýndan çekmek için kullanýlýr
	var form = $(booksFormQuery);
	form.find('input[name="id"]').val(book.id);
	form.find('input[name="title"]').val(book.title);
	form.find('input[name="author"]').val(book.author);
	form.find('input[name="kind"]').val(book.kind);
	form.find('input[name="isbn"]').val(book.isbn);
	form.find('input[name="editionLanguage"]').val(book.editionLanguage);
	form.find('input[name="releaseDate"]').val(book.releaseDate);
	form.find('input[name="description"]').val(book.description);
}

function rowToBook(id) {//?Eriþim Ýçin Mi
	var row = $('#book-' + id);

	return {
		'id': id,
		'title': row.find('td.title').text(),
		'author': row.find('td.author').text(),
		'kind': row.find('td.kind').text(),
		'isbn': row.find('td.isbn').text(),
		'editionLanguage': row.find('td.editionLanguage').text(),
		'releaseDate': row.find('td.releaseDate').text(),
		'description': row.find('td.description').text()

	};
}

function isEditing() {
	return $(booksFormQuery + ' input[name="id"]').val() != "";
}

function disableForm() {
	$(booksFormQuery + ' input').prop('disabled', true);
}

function enableForm() {
	$(booksFormQuery + ' input').prop('disabled', false);
}

function resetForm() {
	$(booksFormQuery)[0].reset();
	$(booksFormQuery + ' input[name="id"]').val('');
	$('#btnAccept').val('Create');
}

function showErrorMessage(jqxhr, textStatus, error) {
	alert(textStatus + ": " + error);
}

function addRowListeners(book) {
	$('#book-' + book.id + ' a.edit').click(function() {
		bookToForm(rowToBook(book.id));
		$('input#btnSubmit').val('Modificar');
	});
	
	$('#book-' + book.id + ' a.delete').click(function() {
		if (confirm('EstÃ¡ a punto de eliminar a una booka. Â¿EstÃ¡ seguro de que desea continuar?')) {
			deleteBook(book.id,
				function() {
					$('tr#book-' + book.id).remove();
				},
				showErrorMessage
			);
		}
	});
}

function appendToTable(book) {
	$(booksListQuery + ' > tbody:last')
		.append(createBookRow(book));
	addRowListeners(book);
}

function initbooks() {
	$.getScript('js/dao/books.js', function() {
		listbooks(function(books) {
			$.each(books, function(key, book) {
				appendToTable(book);
			});
		});
		
		$(booksFormQuery).submit(function(event) {
			var book = formToBook();
			
			if (isEditing()) {
				modifyBook(book,
					function(book) {
						$('#book-' + book.id + ' td.title').text(book.title);
						$('#book-' + book.id + ' td.author').text(book.author);
						$('#book-' + book.id + ' td.kind').text(book.kind);
						$('#book-' + book.id + ' td.isbn').text(book.isbn);
						$('#book-' + book.id + ' td.editionLanguage').text(book.editionLanguage);
						$('#book-' + book.id + ' td.releaseDate').text(book.releaseDate);
						$('#book-' + book.id + ' td.description').text(book.description);
						resetForm();
					},
					showErrorMessage,
					enableForm
				);
			} else {
				addBook(book,
					function(book) {
						appendToTable(book);
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
