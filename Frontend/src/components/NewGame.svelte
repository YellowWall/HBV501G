<script>
    import { onMount } from 'svelte';
    import { Table, TableBody, TableBodyCell, TableBodyRow, TableHead, TableHeadCell } from 'flowbite-svelte';
    let backendRoute = 'http://localhost:8080';
    let selected ='select field';
    let field = [];
    let number = 0

    onMount(async function getField(){
        try {
            const res = await fetch(backendRoute);
            if (!res.ok) {
                throw new Error('Failed to fetch fields');
            }
            const data = await res.json();
            field = data;
        } catch (error) {
            console.error('Error fetching fields: ', error);
        }
            
    });

    async function scorePost(){
        const res = await fetch(
            backendRoute + '/newgame',
            {method: 'POST',
                headers: {"Content-Type": "application/json"},
                body: JSON.stringify({
                    throws
                })
            }
        )
    }
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
    {#each options as value}<options {value}>{value  }</options>{/each}
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
                <TableBodyCell>
                    <input
                    bind:value={number}
                    type="number"
                    name="throws"
                    >
                </TableBodyCell>
            </TableBodyRow>
        {/each}
    </TableBody>
</table>