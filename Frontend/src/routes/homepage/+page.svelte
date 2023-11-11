
<script>
        let backendRoute = 'http://localhost:8080';
        let Username = window.sessionStorage.getItem('Username');
        let authenticatorTocen = window.sessionStorage.getItem('authenticatorTocen');
        //console.log(Username);
        //console.log(authenticatorTocen);
        async function load(){
            const res = await fetch(
                backendRoute + '/user/user',
                {method: 'POST',
                headers: {"Content-Type": "application/json"},
                body: JSON.stringify({
                    username: Username,
                    password: '',
                    salt: '',
                    name: ''
                    })
                }
            )
            //console.log('her er res');
            //console.log(res);
            const data = await res.json();
            //console.log('her er data');
            //console.log(data);
            if (res.ok) {
                return data;
            }
        }

        let user = load();
        console.log("her er user")
        console.log(user.data);
</script>


<h1>This is the user homepage</h1>
<p></p>