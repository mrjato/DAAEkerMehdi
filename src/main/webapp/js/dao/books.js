function listBooks(done, fail, always) {
	done = typeof done !== 'undefined' ? done : function() {};
	fail = typeof fail !== 'undefined' ? fail : function() {};
	always = typeof always !== 'undefined' ? always : function() {};
	
	$.ajax({
		url: 'rest/books',
		type: 'GET'
	})
	.done(done)
	.fail(fail)
	.always(always);
}

function addBook(book, done, fail, always) {
	done = typeof done !== 'undefined' ? done : function() {};
	fail = typeof fail !== 'undefined' ? fail : function() {};
	always = typeof always !== 'undefined' ? always : function() {};
	
	$.ajax({
		url: 'rest/books',
		type: 'POST',
		data: person
	})
	.done(done)
	.fail(fail)
	.always(always);
}

function modifyBook(book, done, fail, always) {
	done = typeof done !== 'undefined' ? done : function() {};
	fail = typeof fail !== 'undefined' ? fail : function() {};
	always = typeof always !== 'undefined' ? always : function() {};
	
	$.ajax({
		url: 'rest/books/' + book.id,
		type: 'PUT',
		data: person
	})
	.done(done)
	.fail(fail)
	.always(always);
}

function deleteBook(id, done, fail, always) {
	done = typeof done !== 'undefined' ? done : function() {};
	fail = typeof fail !== 'undefined' ? fail : function() {};
	always = typeof always !== 'undefined' ? always : function() {};
	
	$.ajax({
		url: 'rest/books/' + id,
		type: 'DELETE',
	})
	.done(done)
	.fail(fail)
	.always(always);
}