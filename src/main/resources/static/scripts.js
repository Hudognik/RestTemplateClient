$(document).ready(function () {
    $('#editUserDlg').on('show.bs.modal', function (event) {
        var dlg = $(this);
        var user_id = $(event.relatedTarget).data('id');

        dlg.find('form').trigger('reset');

        $.ajax({
            url: '/users/' + user_id,
            method: 'GET',
            dataType: 'json'
        })
            .done(function (user) {
                dlg.find('#editid').val(user.id);
                dlg.find('#editemail').val(user.email);
                dlg.find('#editpassword').val(user.password);
                dlg.find('#editfirstname').val(user.firstName);
                dlg.find('#editlastname').val(user.lastName);
                dlg.find('#editroles option').each(function () {
                    var option = $(this);
                    var value = option.attr('value');
                    option.prop('selected', false);
                    for (var i = 0; i < user.roles.length; i++) {
                        if (user.roles[i].name == value) {
                            option.prop('selected', true);
                        }
                    }
                });
                console.log(user);
            });
    });
    $('#editUserDlg .btn-save').on('click', function () {
        var dlg = $(this).parents('#editUserDlg');

        $.ajax({
            url: '/users/update',
            method: 'POST',
            dataType: 'json',
            data: {
                "id": dlg.find('#editid').val(),
                "email": dlg.find('#editemail').val(),
                "password": dlg.find('#editpassword').val(),
                "firstName": dlg.find('#editfirstname').val(),
                "lastName": dlg.find('#editlastname').val(),
                "roles": dlg.find('#editroles').val().toString()
            }
        })
            .done(function (user) {
                var tr = $('#usersList').find('td[scope="row"]:contains("' + user.id + '")').parent('tr');
                tr.children('td').each(function (i, el) {
                    var td = $(el);
                    switch (i) {
                        case 1: { //roles
                            var html = '';
                            for (var i = 0; i < user.roles.length; i++) {
                                html += '<span class="mr-1">' + user.roles[i].name + '</span>';
                            }
                            td.html(html);
                            break;
                        }
                        case 2: { //login
                            td.text(user.firstName);
                            break;
                        }
                        case 3: { //lastname
                            td.text(user.lastName);
                            break;
                        }
                        case 4: { //email
                            td.text(user.email);
                            break;
                        }
                    }
                });
                console.log(user);
            });
        dlg.modal('hide');
    });

    $('#usersList').on('click', '.btn-delete', function () {
        var btn = $(this);
        var tr = btn.parents('tr');

        $.ajax({
            url: '/users/' + 'delete/' + btn.data('id'),
            method: 'POST',
            dataType: 'json'
        })
            .done(function (user) {
                tr.remove();
            });
    });

    $('#saveUserCard .btn-save').on('click', function (event) {
        event.preventDefault();
        var card = $(this).parents('#saveUserCard');

        $.ajax({
            url: '/users/add',
            method: 'POST',
            dataType: 'json',
            data: {
                "email": card.find('#addemail').val(),
                "password": card.find('#addpassword').val(),
                "firstName": card.find('#addfirstname').val(),
                "lastName": card.find('#addlastname').val(),
                "roles": card.find('#addroles').val().toString()
            }
        })
            .done(function (user) {
                card.find('form').trigger('reset');
                var roleStr = '';
                for (var i = 0; i < user.roles.length; i++) {
                    roleStr += '<span class="mr-1">' + user.roles[i].name + '</span>';
                }

                $('<tr>' +
                    '<td scope="row">' + user.id + '</td>' +
                    '<td>' + roleStr + '</td>' +
                    '<td>' + user.firstName + '</td>' +
                    '<td>' + user.lastName + '</td>' +
                    '<td>' + user.email + '</td>' +
                    '<td>' +
                    '   <a href="#" class="btn btn-primary" data-toggle="modal" data-target="#editUserDlg" data-id="' + user.id + '" role="button">Edit</a>' +
                    '   <a href="#" class="btn btn-danger ml-1 btn-delete" data-id="' + user.id + '" role="button">Delete</a>' +
                    '</td>' +
                    '</tr>'
                ).appendTo('#usersList tbody');

                $('#users-list-tab').tab('show');
            });
    });
});