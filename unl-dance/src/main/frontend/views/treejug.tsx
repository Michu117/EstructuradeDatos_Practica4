import { ViewConfig } from '@vaadin/hilla-file-router/types.js';
import { Button, Grid, GridColumn, TextField, VerticalLayout, HorizontalLayout, IntegerField } from '@vaadin/react-components';
import { Notification } from '@vaadin/react-components/Notification';
import { TreeJugService } from 'Frontend/generated/endpoints';
import { useSignal } from '@vaadin/hilla-react-signals';
import handleError from 'Frontend/views/_ErrorHandler';
import { Group, ViewToolbar } from 'Frontend/components/ViewToolbar';

export const config: ViewConfig = {
  title: 'Tree Jug Solver',
  menu: {
    icon: 'vaadin:flask',
    order: 2,
    title: 'Tree Jug',
  },
};

type JugInputFormProps = {
  onSolve: (result: string) => void;
};

function JugInputForm(props: JugInputFormProps) {
  // Estados iniciales
  const jgCapacidad = useSignal(4);
  const jgInicial = useSignal(0);
  const jpCapacidad = useSignal(3);
  const jpInicial = useSignal(0);

  // Estados finales
  const jgFinalCapacidad = useSignal(4);
  const jgFinal = useSignal(2);
  const jpFinalCapacidad = useSignal(3);
  const jpFinal = useSignal(3);

  const isLoading = useSignal(false);

  const solveJugProblem = async () => {
    if (isLoading.value) return;

    try {
      isLoading.value = true;

      const result = await TreeJugService.solveJugProblemSimple(
        jgCapacidad.value, jgInicial.value,
        jpCapacidad.value, jpInicial.value,
        jgFinalCapacidad.value, jgFinal.value,
        jpFinalCapacidad.value, jpFinal.value
      );

      props.onSolve(result || 'Error');
      Notification.show('Búsqueda completada', {
        duration: 3000,
        position: 'bottom-end',
        theme: 'success'
      });

    } catch (error) {
      handleError(error);
    } finally {
      isLoading.value = false;
    }
  };


  return (
    <VerticalLayout className="gap-m">
      <HorizontalLayout className="gap-m items-end">
        <VerticalLayout className="gap-xs">
          <label className="text-s font-medium">Estado Inicial</label>
          <HorizontalLayout className="gap-s">
            <IntegerField
              label="JG Capacidad"
              value={jgCapacidad.value.toString()}
              onValueChanged={(e) => jgCapacidad.value = parseInt(e.detail.value) || 0}
              style={{ width: '120px' }}
            />
            <IntegerField
              label="JG Actual"
              value={jgInicial.value.toString()}
              onValueChanged={(e) => jgInicial.value = parseInt(e.detail.value) || 0}
              style={{ width: '120px' }}
            />
            <IntegerField
              label="JP Capacidad"
              value={jpCapacidad.value.toString()}
              onValueChanged={(e) => jpCapacidad.value = parseInt(e.detail.value) || 0}
              style={{ width: '120px' }}
            />
            <IntegerField
              label="JP Actual"
              value={jpInicial.value.toString()}
              onValueChanged={(e) => jpInicial.value = parseInt(e.detail.value) || 0}
              style={{ width: '120px' }}
            />
          </HorizontalLayout>
        </VerticalLayout>
      </HorizontalLayout>

      <HorizontalLayout className="gap-m items-end">
        <VerticalLayout className="gap-xs">
          <label className="text-s font-medium">Estado Final (Objetivo)</label>
          <HorizontalLayout className="gap-s">
            <IntegerField
              label="JG Capacidad"
              value={jgFinalCapacidad.value.toString()}
              onValueChanged={(e) => jgFinalCapacidad.value = parseInt(e.detail.value) || 0}
              style={{ width: '120px' }}
            />
            <IntegerField
              label="JG Final"
              value={jgFinal.value.toString()}
              onValueChanged={(e) => jgFinal.value = parseInt(e.detail.value) || 0}
              style={{ width: '120px' }}
            />
            <IntegerField
              label="JP Capacidad"
              value={jpFinalCapacidad.value.toString()}
              onValueChanged={(e) => jpFinalCapacidad.value = parseInt(e.detail.value) || 0}
              style={{ width: '120px' }}
            />
            <IntegerField
              label="JP Final"
              value={jpFinal.value.toString()}
              onValueChanged={(e) => jpFinal.value = parseInt(e.detail.value) || 0}
              style={{ width: '120px' }}
            />
          </HorizontalLayout>
        </VerticalLayout>
      </HorizontalLayout>

      <HorizontalLayout className="gap-s">
        <Button
          onClick={solveJugProblem}
          theme="primary"
          disabled={isLoading.value}
        >
          {isLoading.value ? 'Buscando...' : 'Resolver Problema'}
        </Button>
      </HorizontalLayout>
    </VerticalLayout>
  );
}

export default function TreeJugView() {
  const result = useSignal<string>('');

  const onSolve = (newResult: string) => {
    result.value = newResult;
  };

  return (
    <main className="w-full h-full flex flex-col box-border gap-s p-m">
      <ViewToolbar title="Tree Jug Solver">
        <Group>
        </Group>
      </ViewToolbar>

      <VerticalLayout className="gap-l">
        <JugInputForm onSolve={onSolve} />

        {result.value && (
          <VerticalLayout className="gap-s">
            <h3 className="text-l font-medium">Resultado:</h3>
            <div className="bg-contrast-5 p-m rounded-m">
              <pre className="text-s whitespace-pre-wrap font-mono">
                {result.value}
              </pre>
            </div>
          </VerticalLayout>
        )}

        <VerticalLayout className="gap-s">
        </VerticalLayout>
      </VerticalLayout>
    </main>
  );
}
