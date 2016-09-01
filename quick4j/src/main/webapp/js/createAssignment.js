/* 上移按钮点击事件 */
$(document).on("click", ".btnUp", function() {
	var tr = $(this).parents('tr:first');
	var prevTr = tr.prev();
	if (prevTr.length > 0) {
		prevTr.insertAfter(tr);
	}
	console.log(prevTr);
	console.log('up');
	var $tbody = $("#assDetailTab tbody");
	initSuffix($tbody);
});

/* 下移按钮点击事件 */
$(document).on("click", ".btnDown", function() {
	var tr = $(this).parents('tr:first');
	var nextTr = tr.next();
	if (nextTr.length > 0) {
		nextTr.insertBefore(tr);
	}
	var $tbody = $("#assDetailTab tbody");
	initSuffix($tbody);
});

/* 删除按钮点击事件 */
$(document).on("click", ".btnDel", function() {
	var $btnDel = $(this);
	var $tbody = $("#assDetailTab tbody");
	if ($btnDel.is("[href^=javascript:]")) {
		$btnDel.parents("tr:first").remove();
		initSuffix($tbody);
		return false;
	}
});

/* 初始化下标 */
function initSuffix($tbody) {
	$tbody.find('>tr').each(function(i) {
		$(':input', this).each(function() {
			var $this = $(this), name = $this.attr('name'), val = $this.val();

			if (name)
				$this.attr('name', name.replaceSuffix(i));

			if (val && val.indexOf("#index#") >= 0)
				$this.val(val.replace('#index#', i + 1));
		});
	});
}

