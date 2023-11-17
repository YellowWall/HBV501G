<script>
    import { createEventDispatcher, onMount } from 'svelte';
    import { Table, TableBody, TableBodyCell, TableBodyRow, TableHead, TableHeadCell } from 'flowbite-svelte';
    let backendRoute = 'http://localhost:8080';
    let selected ='select field';
    let fields = [];
    let scores = [
        {id: 1, hole: 1, throws: 0},
        {id: 2, hole: 2, throws: 0}
    ];

    function updateScore(id, key, value) {
        scores = scores.map(score => {
            if (score.id === id) {
                return {...score, [key]: value };
            }
            return score;
        })
    }

    function addRow() {
        const newId = scores.length > 0 ? scores[scores.length - 1].id + 1 : 1;
        const newHole = scores.length > 0 ? scores[scores.length - 1].hole + 1 : 1;
        const newRow = { id: newId, hole: newHole, throws: 0 };
        scores = [...scores, newRow];
    }

    //sækir velli
    onMount(async function getFields(){
        const res = await fetch(
            backendRoute + '/field/fields',
            {method: 'GET',
                body: JSON.stringify({
                    fields
                })
            }
        )
    });

    //vistar upplýsingar úr töflunni sem nýjan leik
    async function saveGame(){
        const res = await fetch(
            backendRoute + '/game/',
            {method: 'POST',
                headers: {"Content-Type": "application/json"},
                body: JSON.stringify({
                    scores
                })
            }
        )
    }

</script>

<h1>new game</h1>

<select bind:value={selected}>
    {#each fields as option}<option value={option}>{option}</option>{/each}
</select>

<table shadow>
    <TableHead>
        <TableHeadCell>hole number</TableHeadCell>
        <TableHeadCell>number of throws</TableHeadCell>
    </TableHead>
    <TableBody class="divide-y">
        {#each scores as score (score.id)}
            <TableBodyRow>
                <td>{score.hole}</td>
                <td><input type="number" bind:value={score.throws} on:input={() => updateScore(score.id, 'throws', score.throws)}/></td>
            </TableBodyRow>
        {/each}
    </TableBody>
</table>

<button on:click={addRow}>Add Row</button>
<button on:click={saveGame}>Save Data</button>