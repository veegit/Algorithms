import tempfile, random

with tempfile.NamedTemporaryFile(delete=False) as nf:
    nf.write('\n'.join(str(random.randint(0, 1000000)) for _ in xrange(10000000)))