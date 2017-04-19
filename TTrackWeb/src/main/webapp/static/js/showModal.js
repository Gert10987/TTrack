var employerID = null;

$(document).ready(function() {
    $('.showAll').on('click', function() {
        // Get the record's ID via attribute
        employerID = $(this).attr('data-id');
        var list = null;

        $.ajax({
            type: "GET",
            url: 'http://localhost:8080/tasks-' + employerID,
            dataType : 'json',
            contentType : 'application/json',
            success: function (tasks) {


             $('#tableTasks').bootstrapTable({
                data: tasks
             });

             $('#allTasksModal').modal('show');

            }
        });
    });

    $("#tableTasks").on("click-cell.bs.table", function (field, value, row, $el) {

       var taskId = $el.id - 1;
       window.location.replace('http://localhost:8080/manage-employer-'+ employerID +'-task-' + taskId);
    });

});

function editButtonFormatter(value) {
            return '<button class="btn btn-success custom-width manageEmployer">Edit</button>';
}

function deleteButtonFormatter(value) {
            return '<button class="btn btn-danger custom-width manageEmployer" onclick="deleteTask()">Delete</button>';
}

function editTask(value){
 alert(value);
}









