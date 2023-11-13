<script>
    import { Table, TableBody, TableBodyCell, TableBodyRow, TableHead, TableHeadCell } from 'flowbite-svelte';
    let backendRoute = 'http://localhost:8080';
    let selected ='select field';
    //þarf að skipta options út fyrir velli úr bakenda
    let options = [
        'select field',
        'field 1',
        'field 2'
    ]
    let hole = [
        '1',
        '2',
        '3',
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
        {#each hole as holes, index}
            <TableBodyRow>
                {holes}
                <TableBodyCell><input type="number"></TableBodyCell>
            </TableBodyRow>
        {/each}
    </TableBody>
</table>