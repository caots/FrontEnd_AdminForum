$(document).ready(function () {
    findAllNewsStatusFalse();
});

function findAllNewsStatusFalse() {
    $.ajax({
        type: "GET",
        headers: {
            "Secret": tokenHeader_value,
        },
        url: "api/v1/public/news/status",
        success: function (news) {
            displayOnTable(news);
        },
        error: function (jqXHR, textStatus, errorThrown) {
            errMess(jqXHR, textStatus, errorThrown);
        }
    })
}

function displayOnTable(newses) {
    $("#column-news-check").html(
        "<td>STT </td>" +
        "<td>Title </td>" +
        "<td>Time </td>" +
        "<td>User</td>" +
        "<td>Action</td>"
    );
    const listSize = Object.keys(newses).length;
    if (listSize > 0) {

        let contentRow;
        newses.map(function (news) {
            contentRow += `
                         <tr>
                         <td> ${news.id} </td>
                         <td> ${news.title} </td>
                         <td> ${news.time} </td>
                         <td> ${news.appUser.name} </td>
                         <td>
                              <span name="${news.id}" class="check-news" style="cursor: pointer;color: red">Check</span>&nbsp;
                        </td>
                        </tr>
                       `;
        });
        $("#row-news-check").html(contentRow);
//===== delete =======
        checkedNews();
    }
}

//============ CHECK NEWS ========================
function checkedNews() {
    $('.check-news').click(function () {
        const id = $(this).attr("name");
        console.log("id-news " + id);
        $.ajax({
            type: "POST",
            headers: {
                "Authorization": tokenHeader_admin_value,
            },
            contentType: "application/json",
            url: "api/v1/admin/news/check?id=" + id,
            timeout: 30000,
            success: function () {
                alert('CHECKED SUCCESS');
                return;
            },
            error: function (jqXHR, textStatus, errorThrown) {
                errMess(jqXHR, textStatus, errorThrown);
            }
        });
    });
}
