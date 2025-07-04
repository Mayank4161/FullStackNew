// Fetch and render projects
fetch('admin/ProjectServlet')
.then(res => {
    if (!res.ok) {
        throw new Error(`HTTP error! status: ${res.status}`);
    }
    return res.json();
})
.then(data => {
    let html = '';
    data.forEach(p => {
        // Sanitize data to prevent XSS
        const name = escapeHtml(p.name || '');
        const description = escapeHtml(p.description || '');
        const imagePath = escapeHtml(p.image_path || '');
        html += `
        <div class="card project-card">
            <img class="card-img" src="assets/images/${imagePath}" alt="${name}" onerror="this.src='assets/images/placeholder.jpg'">
            <div class="card-body">
                <h3 class="card-title">${name}</h3>
                <p class="card-desc">${description}</p>
                <button class="read-more-btn" disabled>READ MORE</button>
            </div>
        </div>`;
    });
    const projectsContainer = document.getElementById('projects');
    if (projectsContainer) {
        projectsContainer.innerHTML = html;
    }
})
.catch(error => {
    console.error('Error fetching projects:', error);
    const projectsContainer = document.getElementById('projects');
    if (projectsContainer) {
        projectsContainer.innerHTML = '<p style="text-align:center;color:#666;">Unable to load projects at this time.</p>';
    }
});

// Fetch and render clients
fetch('admin/ClientServlet')
.then(res => {
    if (!res.ok) {
        throw new Error(`HTTP error! status: ${res.status}`);
    }
    return res.json();
})
.then(data => {
    let html = '';
    data.forEach(c => {
        // Sanitize data to prevent XSS
        const name = escapeHtml(c.name || '');
        const description = escapeHtml(c.description || '');
        const designation = escapeHtml(c.designation || '');
        const imagePath = escapeHtml(c.image_path || '');
        html += `
        <div class="card client-card">
            <img class="client-img" src="assets/images/${imagePath}" alt="${name}" onerror="this.src='assets/images/placeholder.jpg'">
            <div class="client-body">
                <p class="client-desc">${description}</p>
                <h4 class="client-name">${name}</h4>
                <span class="client-designation">${designation}</span>
            </div>
        </div>`;
    });
    const clientsContainer = document.getElementById('clients');
    if (clientsContainer) {
        clientsContainer.innerHTML = html;
    }
})
.catch(error => {
    console.error('Error fetching clients:', error);
    const clientsContainer = document.getElementById('clients');
    if (clientsContainer) {
        clientsContainer.innerHTML = '<p style="text-align:center;color:#666;">Unable to load clients at this time.</p>';
    }
});

// Helper function to escape HTML and prevent XSS
function escapeHtml(text) {
    const map = {
        '&': '&amp;',
        '<': '&lt;',
        '>': '&gt;',
        '"': '&quot;',
        "'": '&#039;'
    };
    return text.replace(/[&<>"']/g, function(m) { return map[m]; });
} 