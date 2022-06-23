'use strict';

let replyIndex = {
    init: function () {
        $("#comment-btn-save").on("click", () => {
            this.commentSave();
        });
    },

    replySave: function () {
        let data = {
            content: $("#comment-content").val(),
        }
        let boardId = $("#boardId").val();
        console.log(data);
        console.log(boardId);
        $.ajax({
            type: "POST",
            url: `/board/view/${boardId}/comment`,
            data: JSON.stringify(data),
            contentType: "application/json; charset=utf-8",
            dataType: "text"
        }).done(function (res) {
            alert("댓글작성이 완료되었습니다.");
            location.href = `/board/${boardId}`;
        }).fail(function (err) {
            alert(JSON.stringify(err));
        });
    },

}
replyIndex.init();