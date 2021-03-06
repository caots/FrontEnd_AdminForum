$(document).ready(function () {
    findAllBigCategory(1);
    findAllPageBigNumber();
});

//==================================page=============================.unbind('click')
function pageBigCategory(size) {
    let contentRow = '';
    for (let i = 1; i <= size; i++) {
        contentRow += `<li><a href="#" class="page" id="_${i}" name="${i}" >${i}</a></li>`;
    }
    $(".pagination").html(
        `<li><a href="#" class="prev" id="prev">&laquo</a></li>`
        + contentRow +
        `<li><a href="#" class="next" id="next">&raquo;</a></li>`
    );
    $("#_1").addClass("active");
}

function findAllPageBigNumber() {
    $.ajax({
        type: "GET",
        headers: {
            "Secret": tokenHeader_value,
        },
        url: "api/v1/public/big-category/size",
        success: function (size) {
            console.log(size);
            pageBigCategory(size);

            $('.page').click(function () {
                const page = $(this).attr("name");
                for (let i = 1; i <= size; i++) {
                    var id = "#_" + i;
                    $(id).removeClass("active");
                }
                $(this).addClass("active");
                findAllBigCategory(page);
            });
        },
        error: function (jqXHR, textStatus, errorThrown) {
            errMess(jqXHR, textStatus, errorThrown);
        }
    });
}

//============ Get All Big Category ========================
function findAllBigCategory(page) {
    $.ajax({
        type: "GET",
        headers: {
            "Secret": tokenHeader_value,
        },
        url:  "api/v1/public/big-category/page?page=" + page,
        success: function (bigCategories) {
            $("#column-big-category").html(
                "<td> STT</td>" +
                "<td> Name</td>" +
                "<td>Description</td>" +
                "<td> Action</td>"
            );
            const listSize = Object.keys(bigCategories).length;
            if (listSize > 0) {
                let contentRow = '';
                bigCategories.map(function (bigCategory) {
                    contentRow += `
                        <tr>
                        <td> ${bigCategory.id} </td>
                        <td> ${bigCategory.name} </td>
                         <td> ${bigCategory.description} </td>
                        <td>
                                 <a href="update-big-category?id=${bigCategory.id}" name="${bigCategory.id}"  class="update-big-category" style="cursor: pointer;color: #4285F4">Chỉnh sửa</a>&nbsp;
                                 <span name="${bigCategory.id}" class="delete-big-category" style="cursor: pointer;color: red">Xóa</span>&nbsp;

                        </td>
                        </tr>
                    `;
                });
                $("#row-big-category").html(contentRow);
                $(".body-main .table-responsive tr td").css({
                    "max-width": "260px",
                    "overflow": "-webkit-paged-y"
                });
                //============ delete =============
                deleteBigCategory();
            }
        },
        error: function (e) {
            console.log("Error: " + e);
        }
    });
}

//============ Delete Big Category ========================
function deleteBigCategory() {

    $('.delete-big-category').click(function () {
        const id = $(this).attr("name");
        $.ajax({
            type: "PUT",
            headers: {
                "Authorization": tokenHeader_admin_value,
            },
            contentType: "application/json",
            url:  "api/v1/admin/big-category/delete?id=" + id,
            timeout: 30000,
            success: function () {
                alert('SUCCESS');
                location.href = "big-category"
            },
            error: function (jqXHR, textStatus, errorThrown) {
                errMess(jqXHR, textStatus, errorThrown);
            }
        });
    });
}



