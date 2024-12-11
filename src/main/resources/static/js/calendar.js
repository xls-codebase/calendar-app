const url = "http://localhost:8080/events";
activateEventsTableButtons();

function addEvent(event) {
    fetch(url, {
        method: 'post',
        body: event,
        headers: {
            'Content-Type': 'application/json'
        }
    }).then((response) => {
        if (response.status === 201) {
            console.log("Post succesfully created")
            getEventList();
        }
    }).catch((error) => { console.log(error)});
}

function updateEvent(id, event) {
    fetch(url + `/${id}`, {
        method: 'put',
        body: event,
        headers: {
            'Content-Type': 'application/json'
        }
    }).then((response) => {
        if (response.status === 204) {
            console.log("Event succesfully updated");
            getEventList();
        }
    }).catch((error) => { console.log(error)});
}

function deleteEvent(id) {
    fetch(url + `/${id}`, {
        method: 'delete',
    }).then((response) => {
        if (response.status === 204) {
            console.log("Event deleted");
            getEventList();
    }
    }).catch((error) => { console.log(error)});
}

function getEventList() {
    fetch(url)
        .then(response => {
            if (!response.ok) { throw new Error('Network response was not ok')}
            return response.json(); })
        .then(data => {
            tableRows = createEventTableRows(data);
            tableRows.length === 0 ? showEmpty() : showEventList()
            updateEventsTableData(tableRows);
        })
        .catch(error => { console.log(error)});
}

const form = document.getElementById("event-form");

form.addEventListener("submit", function(e) {
    e.preventDefault();
    const formData = new FormData(form);
    const event = {};
    if (checkIfNotEmpty(formData.get("event-date"))) {
        event.date = formData.get("event-date")
    }
    if (checkIfNotEmpty(formData.get("event-description"))) {
        event.description = formData.get("event-description").trim()
    }
    if (checkIfNotEmpty(formData.get("set-reminder"))) {
        event.timeOfReminder = formData.get("set-reminder").trim()
    }

    jsonEvent = JSON.stringify(event);

    if (checkIfNotEmpty(formData.get("event-id"))) {
        const id = formData.get("event-id")
        updateEvent(id, jsonEvent)
    } else {
        addEvent(jsonEvent);
    }
    form.reset();
})

function checkIfNotEmpty(string) {
    return string.trim().length !== 0 ? true : false;
}

function activateEventsTableButtons() {
    document.querySelectorAll("button.edit").forEach(item => {
        item.addEventListener("click", event => {
            const tableRow = event.target.closest("tr");
            const eventId = tableRow.dataset.eventId;
            form.elements["event-id"].value = eventId;
            form.elements["event-date"].value = tableRow.querySelector("td.table-event-date").innerHTML.split(".").reverse().join("-");
            form.elements["event-description"].value = tableRow.querySelector("td.table-event-description").innerHTML.trim();
            const reminder = tableRow.querySelector("td.table-event-reminder").dataset.eventReminder;
            form.elements["set-reminder"].value = reminder ? reminder : "";
            })
    });

    document.querySelectorAll("button.delete").forEach(item => {
        item.addEventListener("click", event => {
            const tableRow = event.target.closest("tr");
            const eventId = tableRow.dataset.eventId;
            deleteEvent(eventId);
            })
    });
}

function createEventTableRows(jsonEventList) {
    const tableRows = [];
    for (const event of jsonEventList) {

        const tableRow = document.createElement("tr")
        tableRow.setAttribute("data-event-id", event.eventId);

        const tableCell1 = document.createElement("td");
        tableCell1.classList.add("table-event-date", "align-middle");
        tableCell1.innerHTML = event.date.split("-").reverse().join(".");

        const tableCell2 = document.createElement("td");
        tableCell2.classList.add("table-event-description", "align-middle");
        if(event.description) {
            tableCell2.innerHTML = event.description.trim();
        }

        const tableCell3 = document.createElement("td");
        tableCell3.classList.add("table-event-reminder", "align-middle");
        if(event.timeOfReminder) {
            tableCell3.setAttribute("data-event-reminder", event.timeOfReminder);
        }
        if(event.timeOfReminder) {
            tableCell3.innerHTML = event.timeOfReminder;
        }

        const tableCell4 = document.createElement("td");
        tableCell4.classList.add("table-event-date", "align-middle");
        tableCell4.innerHTML = `<button type="button" class="edit btn btn-link">Bearbeiten</button> | <button type="button" class="delete btn btn-link">Löschen</button>`;

        tableRow.append(tableCell1, tableCell2, tableCell3, tableCell4);
        tableRows.push(tableRow);
    }
    return tableRows;
}

function updateEventsTableData(tableRows) {
    table = document.getElementById("events-table");
    while(table.rows[1]) { table.deleteRow(1)}
    tableRows.forEach((tableRow) => table.append(tableRow));
    activateEventsTableButtons();
}

function showEmpty() {
    const emptyMessage = document.getElementById("empty-event-list");
    if(emptyMessage.classList.contains("d-none")) {
        toggleEventTableContent();
    }
}

function showEventList() {
    const eventList = document.getElementById("event-list");
    if(eventList.classList.contains("d-none")) {
        toggleEventTableContent();
    }
}

function toggleEventTableContent() {
    const emptyMessage = document.getElementById("empty-event-list").classList.toggle("d-none");
    const eventList = document.getElementById("event-list").classList.toggle("d-none");
}

