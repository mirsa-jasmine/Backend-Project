const API_URL = "";

let students = [];

let studentToDelete = null;


const form =
    document.getElementById("studentForm");

const nameInput =
    document.getElementById("name");

const departmentInput =
    document.getElementById("department");

const ageInput =
    document.getElementById("age");

const studentIdInput =
    document.getElementById("studentId");

const studentTable =
    document.getElementById("studentTable");

const studentCount =
    document.getElementById("studentCount");

const searchInput =
    document.getElementById("search");

const message =
    document.getElementById("message");

const formTitle =
    document.getElementById("formTitle");

const submitBtn =
    document.getElementById("submitBtn");

const cancelBtn =
    document.getElementById("cancelBtn");

const deleteModal =
    document.getElementById("deleteModal");

const deleteMessage =
    document.getElementById("deleteMessage");

const cancelDelete =
    document.getElementById("cancelDelete");

const confirmDelete =
    document.getElementById("confirmDelete");


/* Load students when page opens */

document.addEventListener(
    "DOMContentLoaded",
    loadStudents
);


/* Refresh */

document
    .getElementById("refreshBtn")
    .addEventListener(
        "click",
        loadStudents
    );


/* Search */

searchInput.addEventListener(
    "input",
    displayStudents
);


/* Add / Update student */

form.addEventListener(
    "submit",
    async function (event) {

        event.preventDefault();

        const student = {

            name:
                nameInput.value.trim(),

            department:
                departmentInput.value.trim(),

            age:
                Number(ageInput.value)
        };


        const id =
            studentIdInput.value;


        try {

            if (id) {

                const response =
                    await fetch(
                        `${API_URL}/students/${id}`,
                        {
                            method: "PUT",

                            headers: {
                                "Content-Type":
                                    "application/json"
                            },

                            body:
                                JSON.stringify(student)
                        }
                    );


                if (!response.ok) {
                    throw new Error();
                }


                showMessage(
                    "Student updated successfully.",
                    "success"
                );

            } else {

                const response =
                    await fetch(
                        `${API_URL}/students`,
                        {
                            method: "POST",

                            headers: {
                                "Content-Type":
                                    "application/json"
                            },

                            body:
                                JSON.stringify(student)
                        }
                    );


                if (!response.ok) {
                    throw new Error();
                }


                showMessage(
                    "Student added successfully.",
                    "success"
                );
            }


            resetForm();

            await loadStudents();

        } catch (error) {

            showMessage(
                "Unable to save student.",
                "error"
            );
        }
    }
);


/* Get all students */

async function loadStudents() {

    try {

        const response =
            await fetch(
                `${API_URL}/students`
            );


        if (!response.ok) {
            throw new Error();
        }


        students =
            await response.json();


        displayStudents();

    } catch (error) {

        showMessage(
            "Unable to load students.",
            "error"
        );
    }
}


/* Display students */

function displayStudents() {

    const search =
        searchInput.value
            .toLowerCase()
            .trim();


    const filtered =
        students.filter(student =>

            String(student.id)
                .includes(search)

            ||

            student.name
                .toLowerCase()
                .includes(search)

            ||

            student.department
                .toLowerCase()
                .includes(search)
        );


    studentTable.innerHTML = "";


    filtered.forEach(student => {

        const row =
            document.createElement("tr");


        row.innerHTML = `

            <td>${student.id}</td>

            <td>${student.name}</td>

            <td>${student.department}</td>

            <td>${student.age}</td>

            <td>

                <div class="action-buttons">

                    <button
                        class="edit-btn"
                        onclick="editStudent(${student.id})">
                        Edit
                    </button>

                    <button
                        class="delete-btn"
                        onclick="deleteStudent(${student.id})">
                        Delete
                    </button>

                </div>

            </td>

        `;


        studentTable.appendChild(row);
    });


    studentCount.textContent =
        `${filtered.length} student${filtered.length === 1 ? "" : "s"}`;


    document
        .getElementById("emptyState")
        .classList.toggle(
            "hidden",
            filtered.length !== 0
        );
}


/* Edit student */

window.editStudent =
    function (id) {

        const student =
            students.find(
                s => s.id === id
            );


        if (!student) {
            return;
        }


        studentIdInput.value =
            student.id;

        nameInput.value =
            student.name;

        departmentInput.value =
            student.department;

        ageInput.value =
            student.age;


        formTitle.textContent =
            `Edit Student #${student.id}`;

        submitBtn.textContent =
            "Update Student";

        cancelBtn.classList.remove(
            "hidden"
        );


        window.scrollTo({
            top: 0,
            behavior: "smooth"
        });
    };


/* Open delete confirmation */

window.deleteStudent =
    function (id) {

        studentToDelete = id;

        deleteMessage.textContent =
            `Are you sure you want to delete student #${id}?`;

        deleteModal.classList.remove(
            "hidden"
        );
    };


/* Cancel deletion */

cancelDelete.addEventListener(
    "click",
    function () {

        studentToDelete = null;

        deleteModal.classList.add(
            "hidden"
        );
    }
);


/* Confirm deletion */

confirmDelete.addEventListener(
    "click",
    async function () {

        if (studentToDelete === null) {
            return;
        }


        const id =
            studentToDelete;


        try {

            const response =
                await fetch(
                    `${API_URL}/students/${id}`,
                    {
                        method: "DELETE"
                    }
                );


            if (!response.ok) {
                throw new Error();
            }


            showMessage(
                "Student deleted successfully.",
                "success"
            );


            await loadStudents();

        } catch (error) {

            showMessage(
                "Unable to delete student.",
                "error"
            );
        }


        studentToDelete = null;

        deleteModal.classList.add(
            "hidden"
        );
    }
);


/* Reset form */

cancelBtn.addEventListener(
    "click",
    resetForm
);


function resetForm() {

    form.reset();

    studentIdInput.value = "";

    formTitle.textContent =
        "Add Student";

    submitBtn.textContent =
        "Add Student";

    cancelBtn.classList.add(
        "hidden"
    );
}


/* Show message */

function showMessage(
    text,
    type
) {

    message.textContent =
        text;

    message.className =
        `message ${type}`;


    setTimeout(
        function () {

            message.classList.add(
                "hidden"
            );

        },
        3500
    );
}