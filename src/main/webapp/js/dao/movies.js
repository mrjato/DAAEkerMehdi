function listMovies(done, fail, always) {
	done = typeof done !== 'undefined' ? done : function() {};
	fail = typeof fail !== 'undefined' ? fail : function() {};
	always = typeof always !== 'undefined' ? always : function() {};
	
	$.ajax({
		url: 'rest/movies',
		type: 'GET'
	})
	.done(done)
	.fail(fail)
	.always(always);
}

function addMovie(movie, done, fail, always) {
	done = typeof done !== 'undefined' ? done : function() {};
	fail = typeof fail !== 'undefined' ? fail : function() {};
	always = typeof always !== 'undefined' ? always : function() {};
	
	$.ajax({
		url: 'rest/movies',
		type: 'POST',
		data: movie
	})
	.done(done)
	.fail(fail)
	.always(always);
}

function modifyMovie(movie, done, fail, always) {
	done = typeof done !== 'undefined' ? done : function() {};
	fail = typeof fail !== 'undefined' ? fail : function() {};
	always = typeof always !== 'undefined' ? always : function() {};
	
	$.ajax({
		url: 'rest/movies/' + movie.id,
		type: 'PUT',
		data: movie
	})
	.done(done)
	.fail(fail)
	.always(always);
}

function deleteMovie(id, done, fail, always) {
	done = typeof done !== 'undefined' ? done : function() {};
	fail = typeof fail !== 'undefined' ? fail : function() {};
	always = typeof always !== 'undefined' ? always : function() {};
	
	$.ajax({
		url: 'rest/movies/' + id,
		type: 'DELETE',
	})
	.done(done)
	.fail(fail)
	.always(always);
}