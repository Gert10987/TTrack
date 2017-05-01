<div class="modal fade" id="allTasksModal" tabindex="-1" role="dialog" aria-hidden="true" >
    <div class="modal-dialog modal-lg" style="display:table;">
        <div class="modal-content">
            <div class="modal-body">
                <table id="tableTasks" class="table table-hover">
                    <thead>
                    <tr>
                        <th data-field="id" id="taskID">ID</th>
                        <th data-field="name">Name</th>
                        <th data-field="description">Description</th>
                        <th data-formatter="editButtonFormatter"></th>
                        <th data-formatter="deleteButtonFormatter"></th>
                    </tr>
                    </thead>
                </table>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-warning" id="newTaskButton">Add New</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>
        </div>
    </div>
</div>

