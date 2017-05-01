var employerID = null;

$(document).ready(function() {
    $('.showAll').on('click', function() {
        employerID = $(this).attr('data-id');
        $.ajax({
            type: "GET",
            url: 'http://localhost:8080/manage-employer/tasks-' + employerID,
            dataType : 'json',
            contentType : 'application/json',
            success: function (tasks) {

             $('#tableTasks').bootstrapTable({});
             $('#tableTasks').bootstrapTable("load", tasks);
             $('#allTasksModal').modal('show');
            }
        });
    });

    $("#tableTasks").on("click-cell.bs.table", function (field, value, row, $el) {

       var taskId = $el.id - 1;
       window.location.replace('http://localhost:8080/manage-employer-'+ employerID +'-task-' + taskId);
    });

    $('#allTasksModal').on('hidden.bs.modal', '.modal', function () {
         $('#tableTasks').clear();
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









