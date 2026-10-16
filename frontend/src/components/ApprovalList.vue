<script setup lang="ts">
</script>

<template>
	<section class="content-section ledger-section" aria-labelledby="ledger-title">
		<div class="section-heading">
			<div><h2 id="ledger-title">Approval Queue</h2></div>
		</div>
		<div class="table-scroll">
			<table class="overtime-table">
				<thead>
					<tr>
						<th scope="col">Request ID</th>
						<th scope="col">Type</th>
						<th scope="col">Employee</th>
						<th scope="col">Request Date</th>
						<th scope="col">Status</th>
                        <th scope="col">Action</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>OT000123</td>
						<td>Overtime Request</td>
						<td>Eldrich Macatangay</td>
						<td>14-Oct-26</td>
						<td>Pending</td>
                        <td>View</td>
					</tr>
                    <tr>
						<td>AO000456</td>
						<td>Actual Overtime</td>
						<td>Eileen Repollo</td>
						<td>13-Oct-26</td>
						<td>Pending</td>
                        <td>View</td>
					</tr>
                    <tr>
						<td>CTO000789</td>
						<td>CTO Request</td>
						<td>Shane Magnaye</td>
						<td>15-Oct-26</td>
						<td>Pending</td>
                        <td>View</td>
					</tr>
				</tbody>
				<tbody>
					<tr><td class="table-empty" colspan="5">Showing 1-3 of 3 records</td></tr>
				</tbody>
			</table>
		</div>
	</section>
</template>