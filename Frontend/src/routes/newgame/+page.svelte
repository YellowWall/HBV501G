<script>
    import { Table, TableBody, TableBodyCell, TableBodyRow, TableHead, TableHeadCell } from 'flowbite-svelte';
    let backendRoute = 'http://localhost:8080';
    let selected ='select field';
    let options = [
        'select field',
        'field 1',
        'field 2'
    ]
    $: console.log('changed selected:', selected)
    $: console.log('updated options:', options)
</script>

<h1>new game</h1>

<select bind:value={selected}>
    {#each options as value}<option {value}>{value  }</option>{/each}
</select>

<table shadow>
    <TableHead>
        <TableHeadCell>hole number</TableHeadCell>
        <TableHeadCell>number of throws</TableHeadCell>
    </TableHead>
    <TableBody class="divide-y">
        <TableBodyRow>
            <TableBodyCell>nr 1</TableBodyCell>
            <TableBodyCell>nr 2</TableBodyCell>
        </TableBodyRow>
        <TableBodyRow>
            <TableBodyCell><input type="text"></TableBodyCell>
            <TableBodyCell><input type="text"></TableBodyCell>
        </TableBodyRow>
    </TableBody>
</table>