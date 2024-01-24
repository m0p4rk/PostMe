function loadPostDetails(postId) {
    $.ajax({
        url: '/postDetail?id=' + postId,
        type: 'GET',
        success: function(post) {
            $('#post-details').html(
                '<h2 id="post-title">' + post.title + '</h2>' +
                '<p id="post-content">' + post.content + '</p>'
            );
            $('#title-edit').val(post.title);
            $('#content-edit').val(post.content);
        },
        error: function() {
            $('#post-details').html('<p>게시글을 불러오는 데 실패했습니다.</p>');
        }
    });
}

$('#edit-btn').click(function() {
    $('#post-details').hide();
    $('#edit-form').show();
});

$('#save-edit').click(function() {
    let updatedTitle = $('#title-edit').val();
    let updatedContent = $('#content-edit').val();
    $.ajax({
        url: '/updatePost',
        type: 'POST',
        data: {
            id: postId,
            title: updatedTitle,
            content: updatedContent
        },
        success: function(response) {
            $('#post-title').text(updatedTitle);
            $('#post-content').text(updatedContent);
            $('#edit-form').hide();
            $('#post-details').show();
        },
        error: function() {
            alert('게시글 수정에 실패했습니다.');
        }
    });
});

$(document).ready(function() {
    let urlParams = new URLSearchParams(window.location.search);
    let postId = urlParams.get('id'); // URL에서 'id' 파라미터의 값을 가져옵니다.
    if (postId) {
        loadPostDetails(postId);
    } else {
        // ID가 없는 경우의 처리 (예: 에러 메시지 표시)
        console.error('No post ID found in URL');
    }
});
