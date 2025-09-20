var stompClient = null;
var username = null;
let replyTo = null;

function connect(name) {
    let socket = new SockJS("/server1");
    stompClient = Stomp.over(socket);

    stompClient.connect({}, function(frame) {
        console.log("✅ Connected: " + frame);

        stompClient.subscribe("/topic/return-to", function(response) {
            let msg = JSON.parse(response.body);
            renderMessage(msg);
        });

        // send join notification
        stompClient.send("/app/message", {}, JSON.stringify({
            sender: name,
            content: "",
            type: "JOIN"
        }));
    }, function(error) {
        console.error("❌ Connection error: ", error);
    });
}

function sendMessage() {
    let msg = $("#message-value").val();
    if (!msg.trim()) return;

    stompClient.send("/app/message", {}, JSON.stringify({
        sender: username,
        content: msg,
        replyTo: replyTo,
        type: "CHAT"
    }));

    $("#message-value").val("");
    replyTo = null;
    $("#reply-container").addClass("d-none");
}

function renderMessage(msg) {
    let time = new Date().toLocaleTimeString([], {hour: '2-digit', minute:'2-digit'});
    let messageElement = "";

    if (msg.type === "JOIN") {
        messageElement = `<div class="message system">👤 ${msg.sender} joined the chat</div>`;
        if ($(`#user-${msg.sender}`).length === 0) {
            $("#user-list").append(`<li id="user-${msg.sender}">${msg.sender}</li>`);
        }
    } else if (msg.type === "CHAT") {
        let cssClass = (msg.sender === username) ? "self" : "other";
        messageElement = `
          <div class="message ${cssClass}" data-content="${msg.content}">
            <strong>${msg.sender}</strong><br>
            ${msg.replyTo ? `<div class="reply-preview">↪ ${msg.replyTo}</div>` : ""}
            ${msg.content}
            <div class="timestamp">${time}</div>
          </div>`;
    }

    $("#messages").append(messageElement);
    $("#messages").scrollTop($("#messages")[0].scrollHeight);

    // enable click-to-reply
    $(".message").off("click").on("click", function() {
        replyTo = $(this).data("content");
        $("#reply-text").text(replyTo);
        $("#reply-container").removeClass("d-none");
    });
}

$(document).ready(() => {
    $("#login").click(() => {
        username = $("#name-value").val().trim();
        if (!username) {
            alert("⚠️ Please enter your name");
            return;
        }
        localStorage.setItem("name", username);
        $("#name-form").addClass("d-none");
        $("#chat-room").removeClass("d-none");
        connect(username);
    });

    $("#send").click(sendMessage);
    $("#message-value").keypress((e) => { if (e.which === 13) sendMessage(); });

    // Emoji picker toggle
    const emojiBtn = document.getElementById("emoji-btn");
    const emojiPicker = document.getElementById("emoji-picker");
    const messageInput = document.getElementById("message-value");

    emojiBtn.addEventListener("click", () => {
        emojiPicker.style.display = emojiPicker.style.display === "none" ? "block" : "none";
    });

    emojiPicker.addEventListener("emoji-click", (event) => {
        messageInput.value += event.detail.unicode;
    });

    // cancel reply
    $("#cancel-reply").click(() => {
        replyTo = null;
        $("#reply-container").addClass("d-none");
    });
});
