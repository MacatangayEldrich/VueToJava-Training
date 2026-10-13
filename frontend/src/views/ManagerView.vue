<template>
  <section class="route-view" aria-labelledby="manager-title">
    <div class="view-heading manager-heading">
      <div>
        <p class="eyebrow">Monday, October 12, 2026 <span class="heading-divider">/</span> Manager space</p>
        <h1 id="manager-title" class="page-title">Team, in<br /><em>good shape.</em></h1>
        <p class="view-lede">A clear view of the people and priorities in your orbit.</p>
      </div>
      <button class="period-select" type="button">This week <span aria-hidden="true">⌄</span></button>
    </div>

    <div class="stat-grid manager-stats">
      <article class="stat-card leave-card">
        <span class="stat-label">Team members</span>
        <strong>12 <small>people</small></strong>
        <span class="stat-foot"><span class="mini-dot"></span> 9 online right now</span>
      </article>
      <article class="stat-card">
        <span class="stat-label">Time off today</span>
        <strong>2 <small>away</small></strong>
        <span class="stat-foot">Everyone else is in</span>
      </article>
      <article class="stat-card">
        <span class="stat-label">Needs your attention</span>
        <strong>3 <small>requests</small></strong>
        <span class="stat-foot">Waiting for your review</span>
      </article>
    </div>

    <div class="content-grid manager-content">
      <section class="content-section requests-section">
        <div class="section-heading">
          <div><p class="eyebrow">Needs a look</p><h2>Time-off requests</h2></div>
          <span class="count-badge">03</span>
        </div>
        <div class="request-list">
          <article class="request-item">
            <span class="person-avatar green-avatar">SK</span>
            <div class="request-person"><strong>Sam Kim</strong><small>Oct 19 – 20 · 2 days</small></div>
            <span class="request-type">Vacation</span>
            <span class="request-state">Review</span>
          </article>
          <article class="request-item">
            <span class="person-avatar coral-avatar">RL</span>
            <div class="request-person"><strong>Riley Lee</strong><small>Oct 23 · 1 day</small></div>
            <span class="request-type">Personal</span>
            <span class="request-state">Review</span>
          </article>
          <article class="request-item">
            <span class="person-avatar blue-avatar">JT</span>
            <div class="request-person"><strong>Jamie Tran</strong><small>Nov 2 – 6 · 5 days</small></div>
            <span class="request-type">Vacation</span>
            <span class="request-state">Review</span>
          </article>
        </div>
      </section>

      <section class="content-section team-section">
        <div class="section-heading">
          <div><p class="eyebrow">People</p><h2>Your team</h2></div>
          <a class="text-link" href="#/manager">View all <span aria-hidden="true">→</span></a>
        </div>
        <div class="team-list">
          <div class="team-person"><span class="person-avatar green-avatar">AM</span><span><strong>Alex Morgan</strong><small>Product design</small></span><span class="presence online"></span></div>
          <div class="team-person"><span class="person-avatar coral-avatar">SK</span><span><strong>Sam Kim</strong><small>Engineering</small></span><span class="presence online"></span></div>
          <div class="team-person"><span class="person-avatar blue-avatar">RL</span><span><strong>Riley Lee</strong><small>Product design</small></span><span class="presence away"></span></div>
        </div>
      </section>
    </div>
  </section>
</template>