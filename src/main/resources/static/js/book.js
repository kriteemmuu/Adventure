// Example: Show an alert when the form is submitted
$(document).ready(function() {
    $(".booking-form form").submit(function(event) {
      event.preventDefault();
      alert("Your trip has been booked!");
    });
  });
  