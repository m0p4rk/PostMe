function loadPosts(page) {
    $.ajax({
        url: '/posts?page=' + page,
        type: 'GET',
        beforeSend: function () {
            $('#posts-container').html('<div>Loading...</div>');
        },
        success: function (response) {
            $('#posts-container').html('');
            response.posts.forEach(post => {
                var postElement = $(
                    '<div class="post-preview">' +
                    '<h3>' + post.title + '</h3>' +
                    '<p>' + post.summary + '</p>' +
                    '<a href="/postDetail?id=' + post.id + '">자세히 보기</a>' +
                    '</div>'
                );
                postElement.on('click', function() {
                    window.location.href = '/postDetail?id=' + post.id;
                });
                $('#posts-container').append(postElement);
            });
        },
        error: function () {
            $('#posts-container').html('<div>게시글을 불러오는 데 실패했습니다.</div>');
        }
    });
}

function setupPagination(totalPages) {
    $('#pagination').html('');
    for (let i = 1; i <= totalPages; i++) {
        $('#pagination').append(
            '<a href="#" onclick="loadPosts(' + i + '); return false;">' + i + '</a> '
        );
    }
}

$(document).ready(function () {
    loadPosts(1);
    setupPagination(10);
});
