/**
 *  提交回复
 */
function post() {
    var questionId = $("#question_id").val();
    var commentContent = $("#comment_content").val();
    comment2target(questionId, 1, commentContent);
}

function comment(e) {
    var commentId = e.getAttribute("data");
    var commentContent = $("#reply-" + commentId).val();
    comment2target(commentId, 2, commentContent);
}

function comment2target(targetId, type, content) {
    if (!content) {
        alert("不能回复空内容");
        return;
    }
    $.ajax({
        type: "POST",
        url: "/comment",
        contentType: "application/json",
        data: JSON.stringify({
            "parentId": targetId,
            "content": content,
            "type": type
        }),
        success: function (response) {
            if (response.code == 200) {
                window.location.reload();
            } else {
                if (response.code == 211) {
                    var isAccepted = confirm(response.message);
                    if (isAccepted) {
                        window.open("https://github.com/login/oauth/authorize?client_id=5968f58600b7ef5001ee&amp;redirect_uri=http://localhost:4396/callback&amp;scope=user&amp;state=1");
                        window.localStorage.setItem("close", true);
                    }
                } else {
                    alert(response.message);
                }
            }
            console.log(response);
        },
        dataType: "json"
    });
}

/**
 * 展开二级评论
 */
function collapseComments(e) {
    var id = e.getAttribute("data");
    var comments = $("#comment-" + id);
    //获取状态标记
    var collapse = e.getAttribute("data-collapse");
    if (collapse) {
        //折叠二级评论
        comments.removeClass("in");
        //清除二级评论状态标记
        e.removeAttribute("data-collapse");
        //去除选中状态
        e.classList.remove("active");
    } else {
        /*$.getJSON("/comment/" + id, function (data) {
            $.each(data.data.reverse(), function (index, comment) {
                var mediaLeftElement = $("<div/>", {
                    "class": "media-left"
                }).append($("<img/>", {
                    "class": "media-object img-rounded",
                    "src": comment.user.avatarUrl
                }));
                var mediaBodyElement = $("<div/>", {
                    "class": "media-body"
                }).append($("<h5/>", {
                    "class": "media-heading",
                    "html": comment.user.name
                })).append($("<div/>", {
                    "html": comment.content
                })).append($("<div/>", {
                    "class": "menu"
                }).append($("<span/>", {
                    "class": "pull-right",
                    "html": moment(comment.gmtCreate).format('YYYY-MM-DD')
                })));
                var mediaElement = $("<div/>", {
                    "class": "media"
                }).append(mediaLeftElement).append(mediaBodyElement);
                var commentElement = $("<div/>", {
                    "class": "col-lg-12 col-md-12 col-sm-12 col-xs-12 comments"
                }).append(mediaElement);
                subCommentContainer.prepend(commentElement);
            });
*/
            //展开二级评论（拼接标签，使其具有展开效果）
            comments.addClass("in");
            //标记二级评论状态（存入）
            e.setAttribute("data-collapse", "in");
            //添加选中状态
            e.classList.add("active");
        });
    }
}