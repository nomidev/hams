var hams = {};

hams.ajax = function (option, callback, errorCallback) {

    console.log(option)
    var defaultOpt = {
        type: 'get',
        contentType: "application/json",
        url: '/admin/menu/api',
        data: {}, // String -> json 형태로 변환
        dataType: 'json', // success 시 받아올 데이터 형
        success: function (data, textStatus, jqXhr) {
            console.log(data);
            console.log(textStatus);
        },
        error: function (xhr, status, error) {
            if (xhr.responseJSON.error) {
                bootbox.alert(xhr.responseJSON.error);
            }
        }
    }

    var options = _.merge(defaultOpt, option);

    console.log(options)

    $.ajax(options);
}

jQuery.fn.serializeObject = function () {
    var obj = null;
    try {
        if (this[0].tagName && this[0].tagName.toUpperCase() == "FORM") {
            var arr = this.serializeArray();
            if (arr) {
                obj = {};
                jQuery.each(arr, function () {
                    obj[this.name] = this.value;
                });
            }
        }
    } catch (e) {
        alert(e.message);
    } finally {
    }
    return obj;
}

