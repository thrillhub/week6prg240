function searchJobs() {
    const searchTerm = document.getElementById('jobSearch').value.toLowerCase();
    const jobs = document.querySelectorAll('.job-card');

    jobs.forEach(job => {
        const title = job.querySelector('h3').textContent.toLowerCase();
        if (title.includes(searchTerm)) {
            job.style.display = 'block';
        } else {
            job.style.display = 'none';
        }
    });
}

function filterJobs() {
    const jobType = document.getElementById('jobType').value;
    const location = document.getElementById('location').value;
    const experience = document.getElementById('experience').value;
    const jobs = document.querySelectorAll('.job-card');

    jobs.forEach(job => {
        const jobDetails = {
            type: job.querySelector('p:nth-child(2)').textContent.toLowerCase(),
            location: job.querySelector('p:nth-child(3)').textContent.toLowerCase(),
            experience: job.querySelector('p:nth-child(4)').textContent.toLowerCase()
        };

        if (
            (jobType === 'all' || jobDetails.type.includes(jobType)) &&
            (location === 'all' || jobDetails.location.includes(location)) &&
            (experience === 'all' || jobDetails.experience.includes(experience))
        ) {
            job.style.display = 'block';
        } else {
            job.style.display = 'none';
        }
    });
}
// Toggle the dropdown visibility when the user icon is clicked
function toggleDropdown() {
    document.getElementById("userDropdown").classList.toggle("show");
}

// Close the dropdown if the user clicks outside of the icon
window.onclick = function(event) {
    if (!event.target.matches('.user-icon, .user-icon *')) {
        var dropdowns = document.getElementsByClassName("dropdown-content");
        for (var i = 0; i < dropdowns.length; i++) {
            var openDropdown = dropdowns[i];
            if (openDropdown.classList.contains('show')) {
                openDropdown.classList.remove('show');
            }
        }
    }
}