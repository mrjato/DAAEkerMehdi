function listMusics(done, fail, always) {
	done = typeof done !== 'undefined' ? done : function() {};
	fail = typeof fail !== 'undefined' ? fail : function() {};
	always = typeof always !== 'undefined' ? always : function() {};
	
	$.ajax({
		url: 'rest/musics',
		type: 'GET'
	})
	.done(done)
	.fail(fail)
	.always(always);
}

function addMusic(music, done, fail, always) {
	done = typeof done !== 'undefined' ? done : function() {};
	fail = typeof fail !== 'undefined' ? fail : function() {};
	always = typeof always !== 'undefined' ? always : function() {};
	
	$.ajax({
		url: 'rest/musics',
		type: 'POST',
		data: music
	})
	.done(done)
	.fail(fail)
	.always(always);
}

function modifyMusic(music, done, fail, always) {
	done = typeof done !== 'undefined' ? done : function() {};
	fail = typeof fail !== 'undefined' ? fail : function() {};
	always = typeof always !== 'undefined' ? always : function() {};
	
	$.ajax({
		url: 'rest/musics/' + music.id,
		type: 'PUT',
		data: music
	})
	.done(done)
	.fail(fail)
	.always(always);
}

function deleteMusic(id, done, fail, always) {
	done = typeof done !== 'undefined' ? done : function() {};
	fail = typeof fail !== 'undefined' ? fail : function() {};
	always = typeof always !== 'undefined' ? always : function() {};
	
	$.ajax({
		url: 'rest/musics/' + id,
		type: 'DELETE',
	})
	.done(done)
	.fail(fail)
	.always(always);
}